package huangyipan.server.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FileDownLoadSocketThread extends FileSocketThread{
	
	public FileDownLoadSocketThread(File file,long filePos)
	{
		try{
			serverSocket=new ServerSocket(port);
			this.file=file;
			this.filePos=filePos;
			
			//socket=serverSocket.accept();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void Task() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("开始上传");
					socket=serverSocket.accept();
					InputStream in=socket.getInputStream();
					Scanner sc=new Scanner(in);
					OutputStream out=socket.getOutputStream();
					BufferedInputStream input=new BufferedInputStream(in);
					while(true)
					{
						String str=sc.nextLine();
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
							}
							if(read==-1)
							{
								break;
							}
							ps.write(buf,0,read);
						}
						ps.flush();
						ps.close();
						System.out.println("上传完成");
						dis.close();
						serverSocket.close();
						out.flush();
						break;	
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	} 
	
}
