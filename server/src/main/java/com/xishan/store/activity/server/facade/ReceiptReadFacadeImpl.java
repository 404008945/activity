package com.xishan.store.activity.server.facade;

import com.xishan.store.activity.api.facade.RecepitReadFacade;
import com.xishan.store.activity.api.request.RecepitPagingRequest;
import com.xishan.store.activity.api.response.RecepitDTO;
import com.xishan.store.activity.server.service.RecepitService;
import com.xishan.store.base.page.Paging;
import com.xishan.store.base.util.Response;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReceiptReadFacadeImpl implements RecepitReadFacade {

    @Autowired
    private RecepitService recepitService;

    @Override
    public Response<Paging<RecepitDTO>> paging(RecepitPagingRequest recepitPagingRequest) {
        try {
            Paging<RecepitDTO> paging = recepitService.paging(recepitPagingRequest);
            return Response.ok(paging);
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }

    }
}
