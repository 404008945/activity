package com.xishan.store.activity.server.service;

import com.alibaba.fastjson.JSON;
import com.xishan.store.activity.api.enums.ActivityStatusEnum;
import com.xishan.store.activity.api.model.Activity;
import com.xishan.store.activity.api.request.ActivityFindRequest;
import com.xishan.store.activity.api.request.ActivityUpdateRequest;
import com.xishan.store.activity.api.response.ActivityDTO;
import com.xishan.store.activity.server.mapper.ActivityMapper;
import com.xishan.store.activity.server.redis.RedisUtil;
import com.xishan.store.activity.server.util.BeanUtil;
import com.xishan.store.base.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    //创建，修改，查看，过期

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActivityMapper activityMapper;


    public Integer create(ActivityUpdateRequest activityUpdateRequest){
        if(activityUpdateRequest == null){
            throw new ServiceException("入参不可为空");
        }
        Activity activity = BeanUtil.convertToBean(activityUpdateRequest,Activity.class);
        int n = activityMapper.insert(activity);
        if (n <= 0) {
            throw new ServiceException("新增失败");
        }
        return n;
    }
    public Integer update(ActivityUpdateRequest activityUpdateRequest){
        if(activityUpdateRequest == null){
            throw new ServiceException("入参不可为空");
        }
        Activity activity = BeanUtil.convertToBean(activityUpdateRequest,Activity.class);
        int n = activityMapper.updateByPrimaryKeySelective(activity);
        if (n <= 0) {
            throw new ServiceException("更新失败");
        }
        //更新成功，失效redis
        redisUtil.del(makeRedisKey(activityUpdateRequest.getId()));
        return n;
    }



    /**
     * 应该生效而未生效的，应该失效而未失效的两种
     */
    public  void switchActivity(){
        //查到时间未生效的
        Activity activity = new Activity();
        activity.setStatus(ActivityStatusEnum.INVALID.getValue());
        List<Activity> activities = activityMapper.listByConditionValid(activity);//应该开启而未开启

        activities.forEach(it->{
            it.setStatus(ActivityStatusEnum.VALID.getValue());
            ActivityUpdateRequest activityUpdateRequest = BeanUtil.convertToBean(it,ActivityUpdateRequest.class);
            update(activityUpdateRequest);
        });
        activity.setStatus(ActivityStatusEnum.VALID.getValue());
        activities = activityMapper.listByConditionValid(activity);//应该开启而未开启
        activities.forEach(it->{
            it.setStatus(ActivityStatusEnum.INVALID.getValue());
            ActivityUpdateRequest activityUpdateRequest = BeanUtil.convertToBean(it,ActivityUpdateRequest.class);
            update(activityUpdateRequest);
        });
        //过期仍然生效的
    }

    public ActivityDTO findById(ActivityFindRequest activityFindRequest){
        //先从redis中查找
        Object redisObj = redisUtil.get(makeRedisKey(activityFindRequest.getId()));
        ActivityDTO activityDTO = null;
        if(redisObj != null) {
            activityDTO = JSON.parseObject(redisUtil.get(makeRedisKey(activityFindRequest.getId())).toString(), ActivityDTO.class);
        }
        if(activityDTO!= null) {
            return activityDTO;
        }
        Activity activity = activityMapper.selectByPrimaryKey(activityFindRequest.getId());
        activityDTO = BeanUtil.convertToBean(activity,ActivityDTO.class);
        redisUtil.set(makeRedisKey(activityFindRequest.getId()),JSON.toJSONString(activityDTO));
        return activityDTO;
    }

    private String makeRedisKey(Integer id){
        return "activity.id:"+id;
    }




}
