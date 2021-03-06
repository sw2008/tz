package tz.bw.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    
    public static void main(String args[]) throws IOException {
       //为了简单起见，所有的异常信息都往外抛
      int port = 9002;
       //定义一个ServerSocket监听在端口8899上
      ServerSocket server = new ServerSocket(port);
      System.out.println("社保卡报文系统");
      System.out.println("主机名:"+server.getInetAddress().getHostName());
      System.out.println("端口:"+server.getLocalPort());
       while (true) {
          //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
          Socket socket = server.accept();
          socket.setSoTimeout(10*1000); //设置超时间为10秒  
          //每接收到一个Socket就建立一个新的线程来处理它
          new Thread(new Task(socket)).start();
       }
    }
    
    /**
     * 用来处理Socket请求的
    */
    static class Task implements Runnable {
  
       private Socket socket;
       
       public Task(Socket socket) {
          this.socket = socket;
       }
       
       public void run() {
          try {
             handleSocket();
          } catch (Exception e) {
             e.printStackTrace();
          }
       }
       
       
	private String tz_qqcs(Reader reader) throws IOException {
	    char chars[] = new char[256];
	    int len;
	    StringBuilder sb = new StringBuilder();
	    String v_s;
	    int i;

	    // 请求
	    while ((len = reader.read(chars)) != -1) {
		v_s = new String(chars, 0, len);
		sb.append(v_s);
		if ((i = sb.indexOf("$$$$")) != -1) {// 遇到$$$$时就结束接收		   
		    break;
		}
	    }
	    return sb.toString();            
       }
       
       private void tz_fhjg(Writer writer,String i_s) throws IOException {
	   //writer.write("请求报文...."+i_s);
           //writer.flush();   
           TzDb v_qqfw = new TzDb();//请求服务处理
           SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss     ");
           Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
           String   str   =   formatter.format(curDate);
           System.out.println("请求开始:"+str);
           String v_s = v_qqfw.tz_qqfw(i_s);
           curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
           str   =   formatter.format(curDate);
           System.out.println("请求结束:"+str);
           writer.write(v_s);
           writer.flush();            
       }
      
       /**
        * 跟客户端Socket进行通信
        * @throws Exception
        */
       private void handleSocket() throws Exception {
          Reader reader = new InputStreamReader(socket.getInputStream(),"GBK");
          Writer writer = new OutputStreamWriter(socket.getOutputStream(),"GBK");
          try {
              String v_s = tz_qqcs(reader);          
              tz_fhjg(writer,v_s);             
          }catch (SocketTimeoutException e) {                
              writer.write("网络超时或结束符错误！");
          }  
          writer.close();
          reader.close();
          socket.close();
       }
       
    }
    
 }
