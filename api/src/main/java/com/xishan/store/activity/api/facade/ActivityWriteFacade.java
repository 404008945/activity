package com.xishan.store.activity.api.facade;

import com.xishan.store.activity.api.request.ActivityUpdateRequest;
import com.xishan.store.base.util.Response;

/**
 * 创建和更新
 */
public interface ActivityWriteFacade {

    Response<Integer> create(ActivityUpdateRequest activityUpdateRequest);

    Response<Integer> update(ActivityUpdateRequest activityUpdateRequest);
}
