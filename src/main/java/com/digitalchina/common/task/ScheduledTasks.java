package com.digitalchina.common.task;

import com.digitalchina.common.utils.DateUtil;
import com.digitalchina.sport.mgr.resource.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(com.digitalchina.common.task.ScheduledTasks.class);
    @Autowired
    private DiscountService discountService;


    //定时任务，如果优惠时间过期了，更改状态
    @Scheduled(cron = "0 0 0 * * ?")
    public void reportCurrentByCron() {
        try {
            if (discountService.updateOverTimeStatus() >0){
                log.info(DateUtil.now()+"########## 更新过期优惠配置状态成功！########## ", DateUtil.now());
            }else {
                log.error(DateUtil.now()+"########## 更新过期优惠配置状态失败！########## ", DateUtil.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(DateUtil.now()+"########## 更新过期优惠配置状态失败！########## ", DateUtil.now());
        }
    }
}
