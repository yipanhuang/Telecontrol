package huangyipan.server.operator;

import huangyipan.server.socket.FileUpLoadSocketThread;

import java.io.File;
import java.util.ArrayList;



public class Ulf implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try{
			int location=cmdBody.indexOf("?");
			int mode;
			String filename;
			if(location==-1)         //不是续传模式
			{
				mode=-1;
				filename=cmdBody;
			} 
			else                     //是续传模式
			{ 
				String filemode=cmdBody.substring(location+1,cmdBody.length());
				
				if(filemode.equals("1"))
				{
					mode=1;
				}else
				{
					mode=-1;
				}
				filename=cmdBody.substring(0,location);
			}
			
			
			File file=Operator.checkFile(filename, mode);
			FileUpLoadSocketThread upload=new FileUpLoadSocketThread(file, file.length());
			upload.Task();
			msgBackList.add("OK");
			msgBackList.add("ulf:"+cmdBody);
			msgBackList.add("8018");
			msgBackList.add(file.length()+"");
		}
		catch(Exception e){
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		return msgBackList;
	}

}
