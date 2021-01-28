package com.xishan.store.activity.server.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xishan.store.activity.api.enums.RecepitStatusEnum;
import com.xishan.store.activity.api.model.Recepit;
import com.xishan.store.activity.api.request.RecepitPagingRequest;
import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.api.request.RecepitUpdateRequest;
import com.xishan.store.activity.api.response.RecepitDTO;
import com.xishan.store.activity.api.response.RecepitResponse;
import com.xishan.store.activity.server.mapper.RecepitMapper;
import com.xishan.store.activity.server.mq.MqService;
import com.xishan.store.activity.server.mq.message.CreateOrderMessage;
import com.xishan.store.activity.server.util.BeanUtil;
import com.xishan.store.base.exception.ServiceException;
import com.xishan.store.base.page.Paging;
import com.xishan.store.trade.api.request.CreateOrderRequest;
import com.xishan.store.usercenter.userapi.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RecepitService {

    @Autowired
    private RecepitMapper recepitMapper;

    @Autowired
    private ActivityService activityService;
    @Autowired
    private MqService mqService;

    @Value("${rocketmq.topic:createOrderTopic}")
    private String topic;




    private String makeCode(Long id){

        return  "unique.code:"+id;

    }

    //下秒杀单，每个人只能下一次单，使用userId拼UUID就ok了.下单,paging两个功能。

    public String doRecepit(RecepitRequest recepitRequest){//摘单,mq消息
        String code = makeCode(UserContext.getCurrentUser().getId());
        Recepit recepit = new Recepit();
        recepit.setActivityId(recepitRequest.getActivityId());
        recepit.setCreatedAt(new Date());
        recepit.setStatus(RecepitStatusEnum.INVALID.getValue());
        recepit.setUserId(UserContext.getCurrentUser().getId());
        int n = recepitMapper.insert(recepit);
        if (n <= 0) {
            throw new ServiceException("创建单据失败");
        }
        CreateOrderMessage createOrderMessage = new CreateOrderMessage();
        createOrderMessage.setCode(code);
        createOrderMessage.setGoodsId(recepitRequest.getGoodId());
        createOrderMessage.setNum(recepitRequest.getNum());
        createOrderMessage.setPayType(recepitRequest.getPayType());
        createOrderMessage.setRecepitId(recepit.getId());
        createOrderMessage.setSkuId(recepitRequest.getSkuId());
        createOrderMessage.setType(recepitRequest.getType());
        createOrderMessage.setUserId(UserContext.getCurrentUser().getId());
        createOrderMessage.setUserName(UserContext.getCurrentUser().getUserName());
        mqService.send(topic,"", JSON.toJSONString(createOrderMessage));
        return "创建单据成功";
        //发送消息
    }

    public Integer update(RecepitUpdateRequest recepitUpdateRequest){
        if(recepitUpdateRequest == null){
            throw new ServiceException("入参不可为空");
        }
        Recepit recepit = BeanUtil.convertToBean(recepitUpdateRequest,Recepit.class);
        int n = recepitMapper.updateByPrimaryKeySelective(recepit);

        if(n<=0){
            throw new ServiceException("更新失败");
        }
        return n;
    }


    public Paging<RecepitDTO> paging(RecepitPagingRequest recepitPagingRequest){
        if(recepitPagingRequest == null){
            throw new ServiceException("入参不可为空");
        }
        Recepit recepit = BeanUtil.convertToBean(recepitPagingRequest,Recepit.class);
        PageHelper.startPage(recepitPagingRequest.getPageNo(),recepitPagingRequest.getPageSize());

        List<Recepit> recepits = recepitMapper.listByCondition(recepit);

        PageInfo<Recepit> pageInfo = new PageInfo<>(recepits);
        Paging<RecepitDTO> paging = new Paging<>();
        paging.setTotal(pageInfo.getSize());
        paging.setPageNo(recepitPagingRequest.getPageNo());
        paging.setPageSize(recepitPagingRequest.getPageSize());
        paging.setData(BeanUtil.convertToBeanList(pageInfo.getList(),RecepitDTO.class));
        return paging;
    }



}
