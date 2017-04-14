package com.digitalchina.common.tasks;

import com.digitalchina.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;


/**
 * Created by 凌小云 on 2017/4/13.
 * 定时器模板使用
 */

@Component//组件注释
@Configurable//自动装备配bean对象（暂做这么理解）
@EnableScheduling//通过@EnableScheduling注解开启对计划任务的支持

public class ScheduledTasks {
    public static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//设置时间样式
    int a = 1;//查到为1没查到为2
    //定时任务1,每五秒执行一次（时间按照任务一，即多久查询一次checkDate是否等于设定值，查到之后执行任务2，并且任务一继续在执行）
    @Scheduled(fixedRate = 5000)//通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime() {

        if (a !=1){
            log.info(DateUtil.now()+" 每隔5秒执行一次,没有到达指定时间checkDate继续查询", DateUtil.now());
           // System.out.println("每隔5秒执行一次 "+dateFormat.format(new Date()));
        }else{
            fixTimeExecution();

        }



    }
    //定时任务2,固定时间执行某项任务（在固定时间checkDate执行报表汇总任务，当时间到达设定的checkDate时，执行）
    @Scheduled(cron = "0 07 20 ? * *" ) //使用cron属性可按照指定时间执行，本例指的是每天20点07分执行；
    //cron是UNIX和类UNIX(Linux)系统下的定时任务
    public void fixTimeExecution(){

      if(a == 1){
            //System.out.println("在指定时间 "+dateFormat.format(new Date())+" 执行");
            log.info(DateUtil.now()+"到达指定时间,汇总报表", DateUtil.now());
        }else{
            reportCurrentTime();
        }

    }


}
/*
a为测试的取值，即为checkDate
1.如果执行从数据库查询checkDate是否等于指定值，即此处为a=1，每五秒查询一次，判断是否查到，没查到的话继续查询
2.查到数据库中的checkDate到了设定值，此时为a=1，就执行带二个定时任务，即汇总报表
*
* 此定时器解决的问题：
* 1.为了实现自动结算的功能，即汇总报表，需要定时查看是否到了指定的checkDate，这个时间是商家自己设定的，所以每分钟需要查询一次到了这个时间没有
* 2.自动结算的定时，每天固定checkDate汇总报表
*
* */