with aa as(
 select
    b.aac001,d.AAC002,d.AAC003,BCC9A1,
    bccl04 je
    ,BCCY02 xsnf,BCCY03 xsnd,CCC023 ffbz,
    CAE064 ffnf,CAE065 ffnd,
    CCC018
    ,pub_func.pub_getaab300(b.AAE017) AAE017 
    ,pub_func.pub_getaab003(b.AAE017) aab003  
   from ac01$ d, cc9a$ b
  where d.AAC001 = b.aac001    
    and b.cae064 = '2011' ---发放年度    
    and ACC03G <> '16' --非社保补贴人员
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
),cc as(
select aac001,x_tzls_sy6y(aac001,max(xsny)) js1,x_tzls_sy6y(aac001,min(xsny))js2
       ,max(xsny) xsny_max,min(xsny)xsny_min
 from bb
group by aac001
),dd as(
  select row_number() over(partition by  substr(aab003,1,4) order by aa.rowid) xh,aa.* from cc,aa where cc.aac001=aa.aac001 and  js1='-93' and js2='-93'
)

select * from dd where xh<=10 order by aab003
