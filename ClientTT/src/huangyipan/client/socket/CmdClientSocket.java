package huangyipan.client.socket;

import huangyipan.client.app.ClientActivity;
import huangyipan.client.data.NetFileData;
import huangyipan.client.operator.ShowRemoteFileHandler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class CmdClientSocket {
	String ip;
	Context context;
	int port;
	Socket socket;
	OutputStreamWriter writer;
	BufferedReader bufferedreader;
	
	Handler handler;
	ArrayList<NetFileData> msgList=new ArrayList<NetFileData>();
	public CmdClientSocket(String ip,int port,Handler handler,Context context)
	{
		this.ip=ip;
		this.port=port;
		this.handler=handler;
		this.context=context;
	}
	public void work(final String path)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				msgList.clear();
				Looper.prepare();
				try {
					InetSocketAddress address=new InetSocketAddress(ip,port);				
					socket=new Socket();												
					socket.connect(address, 2000);
					writeBackMsg(path);
					readBackMsg();
					close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
					
				}
				Looper.loop();	
			}
		}).start();
	}
	public void longconnect()
	{
	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					msgList.clear();
					Looper.prepare();
					try {
					InetSocketAddress address=new InetSocketAddress(ip,port);				
					socket=new Socket();												
						socket.connect(address, 2000);
						InputStream is=socket.getInputStream();
						InputStreamReader reader=new InputStreamReader(is, "UTF-8");
						bufferedreader=new BufferedReader(reader);
						BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
						 writer=new OutputStreamWriter(os,"UTF-8");//���Խ��ַ�����ĳ�"GB2312"
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
						Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
						
					}
					Looper.loop();	
				}
			}).start();
	}
	private void readBackMsg() throws Exception{
		// TODO Auto-generated method stub
    		String str="";
			InputStream is=socket.getInputStream();
			InputStreamReader reader=new InputStreamReader(is, "UTF-8");
			bufferedreader=new BufferedReader(reader);
			str=bufferedreader.readLine();
			int k=Integer.parseInt(str)-2;
			String judge=bufferedreader.readLine();
			if(judge.equals("OK"))
			{
				String cmd=bufferedreader.readLine();
				String con[]=cmd.split(":");
				if(con[0].equals("dir"))
				{
					String path=cmd.substring(con[0].length()+1, cmd.length());
					for(int i=0;i<k;i++)
					{ 
						 NetFileData msg=new NetFileData(bufferedreader.readLine(), path);   
						msgList.add(msg);
					}
			    	Message msg = new Message();
			    	Bundle bundle = new Bundle();    	
			    	bundle.putParcelableArrayList("list",msgList);      	
			    	msg.setData(bundle);  	
			    	handler.sendMessage(msg);
				}else if(con[0].equals("dlf"))
				{
					String port=bufferedreader.readLine();
					String size=bufferedreader.readLine();
					Message msg=new Message();
					Bundle bundle=new Bundle();
					bundle.putString("port", port);
					bundle.putString("size", size);
					msg.setData(bundle);
					
					handler.sendMessage(msg);
				}else if(con[0].equals("ulf"))
				{
					String port=bufferedreader.readLine();
					String size=bufferedreader.readLine();
					Message msg=new Message();
					Bundle bundle=new Bundle();
					bundle.putString("port", port);
					bundle.putString("size", size);
					msg.setData(bundle);
					handler.sendMessage(msg); 
				}
				else
				{
					Toast.makeText(context, cmd, Toast.LENGTH_SHORT).show();
				}	
			}
			else
			{
				String errorMessage=bufferedreader.readLine();
				Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show();
			}
		
		
	}
	public void longreadBackMsg() throws Exception{
		// TODO Auto-generated method stub
    	
    		String str="";
			
			str=bufferedreader.readLine();
			int k=Integer.parseInt(str)-2;
			String judge=bufferedreader.readLine();
			if(judge.equals("OK"))
			{
				String cmd=bufferedreader.readLine();
				String con[]=cmd.split(":");
				
			}
			else
			{
				String errorMessage=bufferedreader.readLine();
				Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show();
			}
			String msg=bufferedreader.readLine();
		
		
	}
	public void longwriteBackMsg(String path) throws Exception {
        // TODO Auto-generated method stub
        

        //          OutputStreamWriter writer=new OutputStreamWriter(os);//Ĭ�ϵ��ַ����룬�п�����GB2312Ҳ�п�����UTF-8��ȡ����ϵͳ
        //          //���鲻Ҫ��Ĭ���ַ����룬����ָ��UTF-8���Ա�֤���ͽ����ַ�����һ�£������ڳ�����
        //��������ֽڴ���ģ������߱��ַ���ֱ��д�빦�ܣ�����ٽ����װ��OutputStreamWriter��ʹ��֧���ַ���ֱ��д��
       
        writer.write("2\n");
        writer.flush();
        writer.write(path+"\n");//δ����д�����������������ڴ���
        writer.flush();//д������������������ݴ����ȥ
        writer.write("%quit%\n");
        writer.flush();
        longreadBackMsg();
    }
	public void writeBackMsg(String path) throws IOException {
        // TODO Auto-generated method stub
        BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
        writer=new OutputStreamWriter(os,"UTF-8");
        writer.write("1\n");
        writer.flush();
        writer.write(path+"\n");
        writer.flush();
        
    }
	public void close() throws IOException
	{
		socket.close();
//		writer.write("1");
//		writer.flush();
////		bufferedreader.close();
////		writer.close();
////		socket.close();
	}
}
