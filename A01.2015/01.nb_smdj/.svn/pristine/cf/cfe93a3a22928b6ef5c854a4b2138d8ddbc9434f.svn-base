package com.nb.smdj;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
  
/** 
 * SimpleTrigger:它只能用于指定任务在一个特定时间内运行,可指定任务的重复(时间,次数)与间隔(时间,次数) 
 * */  
public class NBSmdj { 
    
    public void run() throws Exception {        
        SchedulerFactory sf = new StdSchedulerFactory();  
        Scheduler sched = sf.getScheduler();    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");          
        Date startTime = DateBuilder.nextGivenSecondDate(null,15);// 在当前时间15秒后运行         
        JobDetail job = newJob(NBSmdjJob.class).withIdentity("job1", "group1").build();  
        Date endTime = DateBuilder.nextGivenMinuteDate(null, 5);  
        System.out.println("开始时间: "+ sdf.format(startTime)+",结束时间: "+sdf.format(endTime));  
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")  
        .startAt(startTime)   
        //.endAt(endTime)       
        .withSchedule(  
                simpleSchedule()  
                .withIntervalInSeconds(2)  
                .repeatForever()  
        )                     
        .build();       
        sched.scheduleJob(job, trigger);       
        sched.start();   
        
        //调度器停止  
        //sched.shutdown(true);          
        //Thread.sleep(600L*1000L);
        //SchedulerMetaData metaData = sched.getMetaData();  
        //总共运行了多少个任务  
        //System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");  
    }  
  
    public static void main(String[] args) throws Exception {         
	NBSmdj v_sm = new NBSmdj();  
	v_sm.run();  
    }  
}  