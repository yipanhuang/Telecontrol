package huangyipan.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class FileUpLoadSocketThread extends FileSocketThread{

	public FileUpLoadSocketThread(File file,long filePos) {
		// TODO Auto-generated constructor stub
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
				    	System.out.println("开始下载");
						socket=serverSocket.accept();
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						OutputStream song = new FileOutputStream(file);
						DataInputStream dis = new DataInputStream(
								new BufferedInputStream(in));
						DataOutputStream dos = new DataOutputStream(
								new BufferedOutputStream(song));
						byte[] buffer = new byte[8192];
						while (true) {
							Thread.sleep(100);
							int read = 0;
							if (dis != null) {
								read = dis.read(buffer);
							}
							if (read == -1) {
								break;
							}  
							dos.write(buffer, 0, read);
						}
						System.out.println("文件下载完成");
					    dos.close();
					    dis.close();
					    serverSocket.close();
				    } catch (Exception e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				    } 
				     
			}
		}).start();
	}

}
