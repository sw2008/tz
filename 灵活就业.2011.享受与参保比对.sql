with aa as(
 select
    b.aac001,d.AAC002,d.AAC003,BCC9A1,
    bccl04 je
    ,BCCY02 xsnf,BCCY03 xsnd,CCC023 ffbz,
    CAE064 ffnf,CAE065 ffnd,
    CCC018,
    pub_func.pub_getaab300(b.AAE017) AAE017,  
    pub_func.pub_getaab003(b.AAE017) aab003  
   from ac01$ d, cc9a$ b
  where d.AAC001 = b.aac001    
    and b.cae064 = '2011' ---发放年度    
    and exists(select aac002 from ac01$ gg where substr(gg.aac002,1,17)=substr(d.AAC002,1,17)   group by substr(aac002,1,17) having count(*)<=1) --排除同一身份证有二条的人员
   and exists
  (select 1
           from sc01$ d
          where b.aae017 = d.bsc001
            and substr(bsc003, 1, 1) = '1' ---市辖区
            and d.aab003 like pub_func.pub_getaab003('00000001') || '%') 
  ),bb as( ---字符拆分
  select  
    xsnf||LPad(substr(ccc018,b.id,instr(a.ccc018||',',',',b.id)-b.id), 2, '0') xsny,
    a.*
   from aa a,(select rownum as id from dual connect by rownum<=100 ) b
  where substr(','||a.ccc018,b.id,1) = ','
)

select  XSNY 未参保年月,AAC002 身份证,AAC003 姓名,AAE017 办理机构,JE,XSNF,XSND,FFBZ,FFNF,FFND,CCC018,AAB003,AAC001 人员内码
 from bb where not exists(select * from lev_ac07 cc where cc.AAC001=bb.AAC001 and  AAE030=xsny)
order by aab003,aac001,xsny
