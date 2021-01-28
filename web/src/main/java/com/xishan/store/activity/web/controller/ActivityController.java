package com.xishan.store.activity.web.controller;

import com.xishan.store.activity.api.facade.ActivityReadFacade;
import com.xishan.store.activity.api.facade.ActivityWriteFacade;
import com.xishan.store.activity.api.facade.RecepitWriteFacade;
import com.xishan.store.activity.api.request.ActivityFindRequest;
import com.xishan.store.activity.api.request.ActivityUpdateRequest;
import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.api.request.RecepitUpdateRequest;
import com.xishan.store.activity.api.response.ActivityDTO;
import com.xishan.store.base.annoation.Authority;
import com.xishan.store.base.annoation.ResponseJsonFormat;
import com.xishan.store.base.exception.RestException;
import com.xishan.store.base.util.Response;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value="活动",tags={"活动操作接口"})
@RequestMapping("/activity")
@Controller
public class ActivityController {
    @Reference
    private ActivityReadFacade activityReadFacade;

    @Reference
    private ActivityWriteFacade activityWriteFacade;


    @PostMapping("/findById")
    @ResponseBody
    @ResponseJsonFormat
    @Authority
   public   ActivityDTO findById(ActivityFindRequest activityFindRequest){
        Response<ActivityDTO> response = activityReadFacade.findById(activityFindRequest);
        if (!response.isSuccess()) {
            throw new RestException("查找失败"+response.getMessage());
        }
        return response.getData();
    }

    public Integer create(ActivityUpdateRequest activityUpdateRequest){
        Response<Integer> response = activityWriteFacade.create(activityUpdateRequest);
        if (!response.isSuccess()) {
            throw new RestException("查找失败"+response.getMessage());
        }
        return response.getData();

    }

    public Integer update(ActivityUpdateRequest activityUpdateRequest){
        Response<Integer> response = activityWriteFacade.update(activityUpdateRequest);
        if (!response.isSuccess()) {
            throw new RestException("查找失败"+response.getMessage());
        }
        return response.getData();

    }
}
