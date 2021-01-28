package com.xishan.store.activity.api.facade;


import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.api.request.RecepitUpdateRequest;
import com.xishan.store.base.util.Response;

public interface RecepitWriteFacade {

    Response<String> doRecepit(RecepitRequest recepitRequest);

    Response<Integer> update(RecepitUpdateRequest recepitUpdateRequest);
}
