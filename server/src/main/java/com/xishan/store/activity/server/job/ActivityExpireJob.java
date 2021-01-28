package com.xishan.store.activity.server.job;

import com.xishan.store.activity.server.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivityExpireJob {
    @Autowired
    private ActivityService activityService;

    @Scheduled(fixedRate = 1000)
    public void orderTasks() {//查出所有待付款 ，过期的，然后过期
        activityService.switchActivity();
    }

}
