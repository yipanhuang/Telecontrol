package huangyipan.client.socket;

import huangyipan.client.data.NetFileData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaActionSound;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class FileDownLoadSocketThread extends FileSocketThread{
	String ip;
	int port;
	Handler handler;
	long fileSize;
	Socket socket;
	
	long downLoadFileSize=0;
	Timer timer=new Timer();
	int read;
	File file;
	public FileDownLoadSocketThread(String ip,int port,Handler handler,long fileSize,File file)
	{
		this.ip=ip;
		this.port=port;
		this.handler=handler;
		this.fileSize=fileSize;
		
		this.file=file;
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
					bundle.putInt("percent",(int)(downLoadFileSize*100/fileSize) );
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
			}, 0, 500);
	}
	
	@Override
	public void transferFile() throws Exception {
		// TODO Auto-generated method stub
		try{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();
					   
					    try {
					    	downLoadFileSize=0;
					     socket = new Socket(ip, port);
					     InputStream in = socket.getInputStream();
					     OutputStream out = socket.getOutputStream();
					     out.write(("开始下载" + "\n").getBytes("gbk"));
					     out.flush(); // 清理缓冲，确保发送到服务端
					     OutputStream song = new FileOutputStream(file);
					     DataInputStream dis = new DataInputStream(
					       new BufferedInputStream(in));
					     DataOutputStream dos = new DataOutputStream(
					       new BufferedOutputStream(song));
					     System.out.println("开始下载");
					     byte[] buffer = new byte[8192];
					     while (true) {
					    	 Thread.sleep(100);
					      read = 0;
					      if (dis != null) {
					    	  System.out.println(dis.available());
					       read = dis.read(buffer);
					       downLoadFileSize += read;
					       Log.e("read", read+"");
					        }
					      if (read == -1) {
					    	  timer.cancel();
					    	  Message msg=new Message();
								Bundle bundle=new Bundle();
								bundle.putInt("percent",100 );
								msg.setData(bundle);
								handler.sendMessage(msg);
					       break;
					      }   
					      dos.write(buffer, 0, read);
					     }
					     System.out.println("文件下载完成");
					     dos.close();
					     dis.close();
					     out.close();
					     in.close();
					     song.close();
					     socket.close();
					    } catch (UnknownHostException e) {
					     // TODO Auto-generated catch block
					     e.printStackTrace();
					    } catch (IOException e) {
					     // TODO Auto-generated catch block
					     e.printStackTrace();
					    } catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					    finally {
					    	FileDownLoadSocketThread.this.interrupt();
					    }
					 }
			}).start();
		}
		catch(Exception e){
			e.toString();
		}
		
	}
}
