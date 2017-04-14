package com.digitalchina.common.tasks;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Created by 凌小云 on 2017/4/13.
 * main入口
 */
public class main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduledTasks.class);


    }
}
