package com.xishan.store.activity.api.facade;

import com.xishan.store.activity.api.request.ActivityFindRequest;
import com.xishan.store.activity.api.response.ActivityDTO;
import com.xishan.store.base.util.Response;

public interface ActivityReadFacade {

    Response<ActivityDTO> findById(ActivityFindRequest activityFindRequest);
}
