package com.xishan.store.activity.api.facade;

import com.xishan.store.activity.api.request.RecepitPagingRequest;
import com.xishan.store.activity.api.response.RecepitDTO;
import com.xishan.store.base.page.Paging;
import com.xishan.store.base.util.Response;

public interface RecepitReadFacade {
    Response<Paging<RecepitDTO>> paging(RecepitPagingRequest recepitPagingRequest);
}
