package cn.luoqikun.vue_boot.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lqk
 * @Date: 2019/2/27 17:32
 * @Version: 1.0        Spring的定时任务
 */
//@Component
//@EnableScheduling     //开启定时任务
public class ScheduledTasks {

    //每个三秒执行一次
    @Scheduled(fixedRate = 1000 * 3)
    public void reportCurrentTime() {
        System.out.println("每隔3秒执行一次： " + dateFormat().format(new Date()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron() {
        System.out.println("每一分钟执行一次： " + dateFormat().format(new Date()));
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
