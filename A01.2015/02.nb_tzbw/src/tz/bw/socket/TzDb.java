package tz.bw.socket;


    import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Types;

    import oracle.jdbc.OracleConnection;
import oracle.sql.CLOB;

    public class TzDb {

    private Connection conn = null;
    private String url = null;
    private String user = null;
    private String password = null;

    public TzDb() {
       url = "jdbc:oracle:thin:@166.111.32.21:1521:jhqdb";
       user = "nb_itf";
       password = "nb";
    }


    public Connection getConnection() {

       try {
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("connect is ok");
        return conn;
       } catch (InstantiationException e) {
        e.printStackTrace();
        return null;
       } catch (IllegalAccessException e) {
        e.printStackTrace();
        return null;
       } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return null;
       } catch (SQLException e) {
        e.printStackTrace();
        return null;
       }
    }

    public String tz_qqfw(String i_cs) {
       String v_ret=""; 
       try {	
	  
        Connection v_conn = getConnection();
        CallableStatement v_proc = v_conn.prepareCall("{ call sbk_bwgl_fw(?,?,?,?,?) }");
       
        CLOB v_qqbw = new CLOB((OracleConnection) v_conn);
        v_qqbw = oracle.sql.CLOB.createTemporary(v_conn,false,1);        
        v_qqbw.putString(1,i_cs); //请求报文
        v_proc.setClob(1,v_qqbw);
        v_proc.setString(2, "");
        
        v_proc.registerOutParameter(3, java.sql.Types.CLOB);
        v_proc.registerOutParameter(4, java.sql.Types.INTEGER);//返回值
        v_proc.registerOutParameter(5, java.sql.Types.VARCHAR);//返回说明
        v_proc.execute();    
       
        CLOB v_ydbw = (CLOB) v_proc.getClob(3);//应答报文
        v_ret = ClobToString(v_ydbw);       
       } catch (SQLException ex2) {
        ex2.printStackTrace();
       } catch (Exception ex2) {
        ex2.printStackTrace();
       } finally {
        try {
         if (conn != null) {
          conn.close();
         }
        } catch (SQLException ex1) {
        }
       }
       
       return v_ret;
    }

    
    private static String ClobToString(CLOB clob) throws SQLException, IOException {  
        String reString = "";  
        Reader is = clob.getCharacterStream();// 得到流  
        BufferedReader br = new BufferedReader(is);  
        String s = br.readLine();  
        StringBuffer sb = new StringBuffer();  
        // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING  
        while (s != null) {  
            sb.append(s);  
            s = br.readLine();  
        }  
        reString = sb.toString();  
        return reString;  
    }  

}

