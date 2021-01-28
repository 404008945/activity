package com.xishan.store.activity.server.facade;

import com.xishan.store.activity.api.facade.ActivityWriteFacade;
import com.xishan.store.activity.api.request.ActivityUpdateRequest;
import com.xishan.store.activity.server.service.ActivityService;
import com.xishan.store.base.util.Response;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ActivityWriteFacadeImpl  implements ActivityWriteFacade{
    @Autowired
    private ActivityService activityService;
    @Override
    public Response<Integer> create(ActivityUpdateRequest activityUpdateRequest) {
        try {
          Integer n =  activityService.update(activityUpdateRequest);
          return Response.ok(n);
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }
    }

    @Override
    public Response<Integer> update(ActivityUpdateRequest activityUpdateRequest) {
        try {
            Integer n =  activityService.create(activityUpdateRequest);
            return Response.ok(n);
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }
    }
}
