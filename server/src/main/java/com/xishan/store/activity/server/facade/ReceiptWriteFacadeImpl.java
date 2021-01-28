package com.xishan.store.activity.server.facade;

import com.xishan.store.activity.api.facade.RecepitWriteFacade;
import com.xishan.store.activity.api.model.Recepit;
import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.api.request.RecepitUpdateRequest;
import com.xishan.store.activity.server.service.RecepitService;
import com.xishan.store.base.util.Response;
import com.xishan.store.usercenter.userapi.context.UserContext;
import com.xishan.store.usercenter.userapi.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ReceiptWriteFacadeImpl implements RecepitWriteFacade {
    @Autowired
    private RecepitService recepitService;
    @Override
    public Response<String> doRecepit(RecepitRequest recepitRequest) {
        try {
           String s =  recepitService.doRecepit(recepitRequest);
           return Response.ok(s);
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }
    }

    @Override
    public Response<Integer> update(RecepitUpdateRequest recepitUpdateRequest) {
        try {
            Integer s = recepitService.update(recepitUpdateRequest);
            return Response.ok(s);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }
}
