package com.xishan.store.activity.server.facade;

import com.xishan.store.activity.api.facade.ActivityReadFacade;
import com.xishan.store.activity.api.request.ActivityFindRequest;
import com.xishan.store.activity.api.response.ActivityDTO;
import com.xishan.store.activity.server.service.ActivityService;
import com.xishan.store.base.util.Response;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ActivityReadFacadeImpl implements ActivityReadFacade {
    @Autowired
    private ActivityService activityService;
    @Override
    public Response<ActivityDTO> findById(ActivityFindRequest activityFindRequest) {
        try {
            ActivityDTO activityDTO = activityService.findById(activityFindRequest);
            return Response.ok(activityDTO);
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }

    }
}
