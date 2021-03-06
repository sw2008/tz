package tz.bw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private int port = 9061;//9601;
	private ExecutorService executeService = Executors.newFixedThreadPool(30);
	
	public SocketServer(){
		try {
			serverSocket = new ServerSocket(port);					 
			System.out.println("SocketServer have been started.[" + serverSocket + "]");
			while(true){
					socket = serverSocket.accept();//等待请求
					
					StringBuilder sb = new StringBuilder();
					socket.setSoTimeout(10*1000);  //设置超时间为10秒 
					executeService.submit(new Runnable(){
						public void run(){
							try{
								//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							       InputStream in  = socket.getInputStream();	
							       PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
								
								//读入请求
								byte[] buf = new byte[1024];  
							        int readCount = 0;  
							        while ((readCount = in.read(buf)) != 0) {  
							           //sb.append();
						
							        }  
								
								
								
								
							        //加工处理
								
								//返回处理结果
								
								
								//System.out.println("Server Received:[" + line + "]");
								//out.println("Server Received:[" + line + "]");
								out.close();
								in.close();
							}catch (Exception e) {
								e.printStackTrace();
							}finally{
								try {
									socket.close();
								} catch (IOException e) {
									System.out.println("close socket error.");
									e.printStackTrace();
								}
							}
						}
					});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != serverSocket){
					serverSocket.close();
					System.out.println("serverSocket close");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		new SocketServer();
	}
	
}
