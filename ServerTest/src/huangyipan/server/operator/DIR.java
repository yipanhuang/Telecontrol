package huangyipan.server.operator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DIR implements BaseOperator{
	
	public  ArrayList<String> exe(String cmdBody){
		ArrayList<String> msgBackList=new ArrayList<String>();
		try{
			msgBackList.add("OK");
		if(cmdBody.equals("..."))
		{
			 File[] paths=File.listRoots();
			 msgBackList.add("dir:...");
			 for(File mfile:paths)
			 {	 
				 String fileName = mfile.getPath();
			     long lastModified = mfile.lastModified();//��ȡ�ļ��޸�ʱ��
			     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʱ���ʽ�����磺2018-03-16 09:50:23
			     String fileDate = dateFormat.format(new Date(lastModified));//ȡ���ļ�����޸�ʱ�䣬������ʽתΪ�ַ���
			     String fileSize="0";
			     String filetype="2";
			     fileSize=""+mfile.length();
			     System.out.println(fileName+","+fileSize);
			     msgBackList.add(fileName+">"+fileDate+">"+fileSize+">"+filetype+">");
			 }
		}
		else
		{
			 File file = new File(cmdBody);
			 File[] listFiles = file.listFiles();
			 msgBackList.add("dir:"+cmdBody);
			 for(File mfile:listFiles){
			      String fileName = mfile.getName();
			      long lastModified = mfile.lastModified();//��ȡ�ļ��޸�ʱ��
			      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʱ���ʽ�����磺2018-03-16 09:50:23
			      String fileDate = dateFormat.format(new Date(lastModified));//ȡ���ļ�����޸�ʱ�䣬������ʽתΪ�ַ���
			      String fileSize="0";
			      String filetype="0";
			      if(mfile.isDirectory())
			      {
			        	filetype="1";
			      }
			      else {//�ж��Ƿ�ΪĿ¼
			        	
			        	filetype="0";   
			      }
			      fileSize=""+mfile.length();
			      System.out.println(fileName+","+fileSize);
			      msgBackList.add(fileName+">"+fileDate+">"+fileSize+">"+filetype+">");
			    }
			}
		}
		catch(Exception e){
			
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
			return msgBackList;
		}
		
		
		return msgBackList;
	}
}
