package com.nb.smdj;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerMetaData;
  
public class NBSmdjJob implements Job {     
    public static String xf_zt; //任务状态 
    private  NBSjj v_ms_sjj,v_ms_sjj2,v_or_sjj;
    
    public NBSmdjJob() {    }  
      
    public void execute(JobExecutionContext context) throws JobExecutionException {  
	if("ZX".equals(xf_zt)) return ;
	xf_zt = "ZX";
        JobKey jobKey = context.getJobDetail().getKey();          
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
        try {
          SchedulerMetaData metaData = context.getScheduler().getMetaData();         
          System.out.println("Quarta JobKey : " + jobKey + " 执行时间:  " + sdf.format(new Date()) + ";Executed " + metaData.getNumberOfJobsExecuted() + " jobs."); 
          
          v_ms_sjj = new NBMssql("NbrcFileManage");  v_ms_sjj2 = new NBMssql("ZX_NbrcFileManager"); //毕业生系统
          v_or_sjj = new NBOracle(); //就业系统
          v_ms_sjj.vp_Connect0();v_ms_sjj2.vp_Connect0();
          v_or_sjj.vp_Connect0();
          for (String vf_s1 : NBSjj.wf_bms_jy) {
              ResultSet vf_rs = v_ms_sjj.xp_read(vf_s1, 0, -1);
              v_or_sjj.xp_write(vf_s1, vf_rs);
              v_or_sjj.vp_log(vf_s1);
          }
          v_or_sjj.xp_rygl(""); //建立人员关联
          
          
          for (String vf_s1 : NBSjj.wf_bms_bys) {
              ResultSet vf_rs = v_or_sjj.xp_read(vf_s1, 0, -1);
              v_ms_sjj2.xp_write(vf_s1, vf_rs);
          }
          xf_zt = "TZ";         
          
        }catch (Exception ex) {
            System.out.println("##"+ex.getMessage());  
        }     
        finally {
            v_ms_sjj.vp_close();            
            v_ms_sjj2.vp_close(); 
            v_or_sjj.vp_close();
        }
    }  
}  