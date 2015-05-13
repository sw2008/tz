package com.nb.smdj;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NBOracle extends NBSjj{
   

    public NBOracle() throws IOException {
       wf_url = "jdbc:oracle:thin:@166.111.32.21:1521:jhqdb";
       wf_user = "nb_itf_smdj";
       wf_password = "smdj";
       wf_driver = "oracle.jdbc.driver.OracleDriver";
       vp_csh(ORACLE_DB);
    }
    
    protected Connection vp_Connect0() {
	Connection vf_c = super.vp_Connect0();
        //加载同步表
	PreparedStatement vf_ps;
	try {
	    
	    //毕业生系统的表
	    vf_ps = wf_conn.prepareStatement("select * from zx_nb_tbb$sj  where tbfx='T' order by xh");
	    ResultSet vf_rs = vf_ps.executeQuery();wf_bms_jy.clear();
	    while (vf_rs.next()){
		wf_bms_jy.add(vf_rs.getString("BM"));
	    }
	    vf_rs.close();vf_rs = null;vf_ps.close();vf_ps = null;
	    
	    //就业系统的表
	    vf_ps = wf_conn.prepareStatement("select * from zx_nb_tbb$sj  where tbfx='G' order by xh");
	    vf_rs = vf_ps.executeQuery();wf_bms_bys.clear();
	    while (vf_rs.next()){
		wf_bms_bys.add(vf_rs.getString("BM"));
	    }
	    vf_rs.close();vf_rs = null;vf_ps.close();vf_ps = null;
	    
	} catch (SQLException e) {	    
	    e.printStackTrace();
	} 
	
	
	
	
	return vf_c;
    }  
    
  protected void xp_write8(String i_bm) throws SQLException {      
        CallableStatement cstmt = null;      
        String procedure = "{call smd_tb_kz(?,?,?,?,?,?,?)}";
        cstmt = wf_conn.prepareCall(procedure);
        cstmt.setString(1,i_bm);
        cstmt.setString(2,"");cstmt.setString(3,"");cstmt.setString(4,"");cstmt.setString(5,"");
        
        cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
        cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
        cstmt.executeUpdate();
  }
  
  public void xp_rygl(String i_bm) throws SQLException {      
//      CallableStatement cstmt = null;      
//      String procedure = "{call smd_rysm_gl(?,?,?,?,?,?,?)}";
//      cstmt = wf_conn.prepareCall(procedure);
//      cstmt.setString(1,i_bm);
//      cstmt.setString(2,"");cstmt.setString(3,"");cstmt.setString(4,"");cstmt.setString(5,"");
//      
//      cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
//      cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
//      cstmt.executeUpdate();
  }
  

}
