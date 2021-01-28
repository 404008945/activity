package com.xishan.store.activity.web.controller;

import com.xishan.store.activity.api.facade.RecepitReadFacade;
import com.xishan.store.activity.api.facade.RecepitWriteFacade;
import com.xishan.store.activity.api.request.RecepitPagingRequest;
import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.api.response.RecepitDTO;
import com.xishan.store.base.annoation.Authority;
import com.xishan.store.base.annoation.ResponseJsonFormat;
import com.xishan.store.base.exception.RestException;
import com.xishan.store.base.page.Paging;
import com.xishan.store.base.util.Response;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value="活动单据",tags={"单据操作接口"})
@RequestMapping("/receipt")
@Controller
public class RecepitController {
    @Reference
    private RecepitReadFacade recepitReadFacade;
    @Reference
    private RecepitWriteFacade recepitWriteFacade;

    @PostMapping("/paging")
    @ResponseBody
    @ResponseJsonFormat
    @Authority
    public Paging<RecepitDTO> paging(RecepitPagingRequest recepitPagingRequest){
        Response<Paging<RecepitDTO>> response = recepitReadFacade.paging(recepitPagingRequest);
        if(response.isSuccess()){
            throw new RestException("分页查询失败"+response.getMessage());
        }
        return response.getData();
    }

    @PostMapping("/doRecepit")
    @ResponseBody
    @ResponseJsonFormat
    @Authority
    public String doRecepit(RecepitRequest recepitRequest) {
        Response<String> response = recepitWriteFacade.doRecepit(recepitRequest);

        if(response.isSuccess()){
            throw new RestException("分页查询失败"+response.getMessage());
        }
        return response.getData();
    }



}
