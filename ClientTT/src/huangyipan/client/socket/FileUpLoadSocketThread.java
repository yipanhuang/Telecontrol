package huangyipan.client.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class FileUpLoadSocketThread extends FileSocketThread{

	String ip;
	int port;
	Socket socket;
	File file;
	Handler handler;
	long downLoadFileSize=0;
	long totalsize;
	Timer timer=new Timer();

	public FileUpLoadSocketThread(String ip,int port,File file,Handler handler,long totalsize,long alreadysize) {
		// TODO Auto-generated constructor stub
		this.ip=ip;
		this.port=port;
		this.file=file;
		this.handler=handler;
		this.totalsize=totalsize;

		init();
		
	}
	private void init() {
		// TODO Auto-generated method stub
		timer.schedule(new TimerTask() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=new Message();
				Bundle bundle=new Bundle();
				bundle.putInt("percent",(int) (100*downLoadFileSize/totalsize));
				msg.setData(bundle);
				handler.sendMessage(msg);
			
			}
		}, 0, 500);
		 
	}
	@Override
	public void transferFile() throws Exception {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("开始上传");
					socket=new Socket(ip,port);
					InputStream in=socket.getInputStream();
					OutputStream out=socket.getOutputStream();
					BufferedInputStream input=new BufferedInputStream(in);
					while(true)
					{
						FileInputStream fis=new FileInputStream(file);
						DataInputStream dis=new DataInputStream(new BufferedInputStream(fis));
						byte[] buf=new byte[8192];
						DataOutputStream ps=new DataOutputStream(out);
						while(true)
						{
							Thread.sleep(100);
							int read=0;
							if(dis!=null){
								read=fis.read(buf);
								downLoadFileSize+=read;
								
							}
							if(read==-1)
							{
								timer.cancel();
								Message msg=new Message();
								Bundle bundle=new Bundle();
								bundle.putInt("percent",100 );
								msg.setData(bundle);
								handler.sendMessage(msg);
								break;
							}
							ps.write(buf,0,read);
						}
						ps.flush();
						out.flush();
						ps.close();
						dis.close();
						socket.close();
						System.out.println("上传完成");
						 
						break;
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
		}).start();
	}

}
