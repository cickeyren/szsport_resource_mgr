package com.digitalchina.common.quartz;

import com.digitalchina.common.quartz.util.DailyTime;
import com.digitalchina.sport.mgr.resource.service.MerchantService;
import com.digitalchina.sport.mgr.resource.service.StatisticsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_ on 2017/4/20.
 */
@Component
public class DailyOrderQuartz {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(DailyOrderQuartz.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private StatisticsService statisticsService;

    /**
     * 每分钟执行 查看商户统计时间，统计
     */
//    @Scheduled(cron = "0 0/1 * * * ? ")
    public void daily() {
        Calendar calendar=Calendar.getInstance();
        Date daily = calendar.getTime();
        String dailyTime = sdf.format(new Date());
        System.out.println("time=" + dailyTime);
        List<Map<String, String>> merchants = merchantService.getMerchants();
        int len = merchants.size();
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            Map<String, String> temp = merchants.get(i);
            if (dailyTime.equals(temp.get("settlementTime"))) {//如果是统计时间
                String sTime = DailyTime.getYesterday(daily,"yyyy-MM-dd HH:mm");
                String eTime = DailyTime.format(daily,"yyyy-MM-dd HH:mm");
                try {
                    //开始统计
                    new Thread(new StatisticOrder(temp.get("id"), temp.get("mainStadiumId"), sTime, eTime,statisticsService)).start();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
