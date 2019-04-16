package com.fanshuaiko.backage.Task;

import com.fanshuaiko.backage.dao.TestDao;
import com.fanshuaiko.backage.dict.TestStatus;
import com.fanshuaiko.backage.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DynamicUpdateTestStutasTask
 * @Description 自动更新考试状态任务
 * @Author fanshuaiko
 * @Date 19-4-3 上午10:35
 * @Version 1.0
 **/
@Component
@Configuration
@EnableScheduling //开启定时任务
public class DynamicUpdateTestStatusTask implements SchedulingConfigurer {

    @Autowired
    TestDao testDao;

    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //执行任务
        scheduledTaskRegistrar.addTriggerTask(() -> {
                    System.out.println("---------正在执行定时任务：" + LocalDateTime.now().toLocalTime());
                    List<Test> allTest = testDao.getAllTest();
                    for (Test test : allTest) {
                        long startTime = test.getStartTime().getTime();
                        long endTime = test.getEndTime().getTime();
                        long nowTime = new Date().getTime();
                        System.out.println("当前时间：" + new Date());
                        if ((nowTime - startTime) >= 0 && (nowTime - endTime) < 0) {
                            //将考试状态置为进行中
                            if (!test.getStatus().equals(TestStatus.Running.getCODE())) {
                                testDao.setRunningStatus(test.getId());
                                System.out.println("已将考试" + test.getId() + ":" + test.getName() + " 的状态置为进行中");
                            }
                        }
                        if ((nowTime - endTime) >= 0) {
                            //将考试状态置为已结束
                            if (!test.getStatus().equals(TestStatus.Finished.getCODE())) {
                                testDao.setFinishedStatus(test.getId());
                                System.out.println("已将考试" + test.getId() + ":" + test.getName() + " 的状态置为已结束");
                            }
                        }
                    }
                },//设置执行周期
                triggerContext -> new CronTrigger("0/30 * * * * ?").nextExecutionTime(triggerContext));
    }
}
