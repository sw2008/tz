package com.nb.smdj;

import java.io.IOException;

public class NBMssql extends NBSjj{  

    public NBMssql() throws IOException {
	this("NbrcFileManager");
    }   
    
    //数据库名
    public NBMssql(String i_dbn) throws IOException {
	      wf_url = "jdbc:sqlserver://166.111.2.28:1433;databasename="+i_dbn; //"jdbc:sqlserver://192.168.100.10:1433;databasename="+i_dbn;
	      wf_user = "sa";
	      wf_password = "wd12345678";
	      wf_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	      //vp_csh(MSSQL_DB);      
	      
   }       
    
}
