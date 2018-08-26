package huangyipan.server.operator;

import huangyipan.server.socket.FileDownLoadSocketThread;

import java.io.File;
import java.util.ArrayList;

public class Dlf implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBacklist=new ArrayList<String>();
		try{
		File file=new File(cmdBody);
		msgBacklist.add("OK");
		msgBacklist.add("dlf:"+cmdBody);
		msgBacklist.add("8018");
		msgBacklist.add(file.length()+"");
		FileDownLoadSocketThread download=new FileDownLoadSocketThread(file, 01);
		download.Task();
		
		}
		catch(Exception e){
			msgBacklist.add("FAIL");
			msgBacklist.add(e.toString());
		}
		return msgBacklist;
	}

}
