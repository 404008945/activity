package com.xishan.store.activity.server;

import com.xishan.store.activity.api.request.RecepitRequest;
import com.xishan.store.activity.server.service.RecepitService;
import com.xishan.store.usercenter.userapi.context.UserContext;
import com.xishan.store.usercenter.userapi.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerStarterApplicationTests {

    @Autowired
    private RecepitService recepitService;

    @Test
    void contextLoads() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(6l);
        UserContext.putCurrentUser(userDTO);
        RecepitRequest recepitRequest = new RecepitRequest();
        recepitRequest.setActivityId(1);
        recepitRequest.setGoodId(1);
        recepitRequest.setSkuId(1);
        recepitRequest.setNum(1);
        recepitRequest.setPayType((byte) 1);
        recepitRequest.setType((byte) 1);
        recepitRequest.setStatus(1);
        recepitService.doRecepit(recepitRequest);
    }

}
