package huangyipan.server.socket;

import huangyipan.server.operator.Operator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.rmi.runtime.Log;

public class CmdServerSocketThread extends Thread {
	
	ArrayList<String> cmdList;
	boolean islong=false;
	ArrayList<String> msgBackList=new ArrayList<String>();
	ServerSocket serverSocket;
	int port=8019;
	public CmdServerSocketThread()
	{
		
	}
	
	public CmdServerSocketThread(int port)
	{
		super();
		this.port=port;
	}
	@Override
	public void run()
	{
		
		try {
			serverSocket = new ServerSocket(port);
			docmdTask(serverSocket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void docmdTask(ServerSocket serverSocket) throws Exception{
		while(true)
		{
			System.out.println("Wait client to connect...");
			Socket socket=serverSocket.accept();
			InetAddress inetAddress=socket.getInetAddress();
			System.out.println("Client connect from:"+socket.getRemoteSocketAddress().toString());
			try{
				do{
				getAndDealcmd(socket);
				writeBackMsg(socket);
				}while(islong);
				}catch(Exception e){
					writeError(socket,e);	
				}
			System.out.println("��ǰ���ӽ���");
		}
	}
	
	public ArrayList<String> readSocketMsg(Socket socket) throws IOException {
	    // ��socket���������������socket�������Ѿ����ӳɹ�δ���ڹرյ�socket
	    cmdList=new ArrayList<String>();
	    islong=false;
	    InputStream inputStream = socket.getInputStream();
	    InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
	    BufferedReader bufferedReader=new BufferedReader(reader);
	    String lineNumStr = bufferedReader.readLine();
	    int lineNum=Integer.parseInt(lineNumStr);
	    for(int i=0;i<lineNum;i++){
	        String str = bufferedReader.readLine();
	        if(str.equals("%quit%"))
	        {
	        	islong=true;
	        	break;
	        }
	        cmdList.add(str);
	    }
	    return cmdList;
	}   
	private void writeBackMsg(Socket socket) throws Exception {
	    // TODO Auto-generated method stub
	    BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());//socket.getOutputStream()���������BufferedOutputStream�����װΪ������������

	    OutputStreamWriter writer=new OutputStreamWriter(os,"UTF-8");//���Խ��ַ�����ĳ�"GB2312"
	    writer.write(""+msgBackList.size()+"\n");//δ����д�����������������ڴ���
	    writer.flush();//д������������������ݴ����ȥ
	    for(int i=0;i<msgBackList.size();i++){
	        writer.write(msgBackList.get(i)+"\n");
	        writer.flush();
	    }
	    if(islong==true)
	    {
	    	writer.write("%quit%\n");
	    	writer.flush();
	    	islong=false;
	    	getAndDealcmd(socket);
	    }
	    
	}
	private void writeError(Socket socket,Exception e) throws IOException {
		BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
	    OutputStreamWriter writer=new OutputStreamWriter(os,"UTF-8");//���Խ��ַ�����ĳ�"GB2312"
	    writer.write("1\n");
	    writer.write(e.toString());//δ����д�����������������ڴ���
	    writer.flush();//д������������������ݴ����ȥ
		
	}
	public void getAndDealcmd(Socket socket) throws Exception {
		cmdList=readSocketMsg(socket);
		if(cmdList.size()==1)
		{
			String cmd=cmdList.get(0);
			processCmd(cmd);
		}
		if(islong==true)
		{
			writeBackMsg(socket);
		}
	}
	private void processCmd(String cmd) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Client Command:"+cmd);
		int splitIdx=cmd.indexOf(":");
		if(splitIdx<1)
		{
			cmdFail();
			return;
		}
		String cmdHead=cmd.substring(0,splitIdx);
		String cmdBody=cmd.substring(splitIdx+1);
		System.out.println(cmdBody);
		msgBackList=Operator.execmd(cmdHead.toLowerCase(), cmdBody);//������ת��ΪСд
	}
	private void cmdFail() {
		// TODO Auto-generated method stub
		msgBackList.clear();
	}
	
}
