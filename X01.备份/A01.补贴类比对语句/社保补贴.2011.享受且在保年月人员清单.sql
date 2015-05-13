with aa as(
 select
    b.aac001,d.AAC002,d.AAC003,--BCC9A1,
    bccl04 je
    ,BCCY02 xsnf,BCCY03 xsnd,CCC023 ffbz,
    CAE064 ffnf,CAE065 ffnd,
    CCC022 ccc018,
    pub_func.pub_getaab300(b.AAE017) AAE017,  
    pub_func.pub_getaab003(b.AAE017) aab003
   from ac01$ d,CCY2$ b,ccy1$ c
  where b.BCCY01=c.BCCY01
    and d.AAC001 = b.aac001    
    and c.BCCY04 is null  --��ҵ�籣����
    and b.cae064 = '2011' ---�������    
    and exists(select aac002 from ac01$ gg where gg.aac002=d.AAC002  group by aac002 having count(*)<=1 ) --�ų�ͬһ���֤�ж�������Ա
    and exists
  (select 1
           from sc01$ d
          where b.aae017 = d.bsc001
            and substr(bsc003, 1, 1) = '1' ---��Ͻ��
            and d.aab003 like pub_func.pub_getaab003('00000001') || '%')
),bb as( ---�ַ����
  select  
    xsnf||LPad(substr(ccc018,b.id,instr(a.ccc018||',',',',b.id)-b.id), 2, '0') xsny,
    a.*
   from aa a,(select rownum as id from dual connect by rownum<=100 ) b
  where substr(','||a.ccc018,b.id,1)=','
)

select XSNY δ�α�����,AAC002 ���֤,AAC003 ����,AAE017 �������,JE,XSNF,XSND,FFBZ,FFNF,FFND,CCC018,AAB003,AAC001 ��Ա����
from bb where not exists(select * from lev_ac07 cc where cc.AAC001=bb.AAC001 and AAE030=xsny)
order by aab003,aac001,xsny



			
