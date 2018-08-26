package huangyipan.server.operator;

import java.io.File;
import java.util.ArrayList;

public class Operator {
	public static File checkFile(String filename, int mode)
	{
		File file;
		
		if(mode==-1)
		{
			file=new File(filename);
			System.out.println(filename);
			if(file.exists())
			{
				int location=filename.lastIndexOf(".");
				
				int location2=filename.lastIndexOf("\\");
				String Path=filename.substring(0,location2+1);
				
				String realname=filename.substring(location2+1,location);
				
				String suffix=filename.substring(location,filename.length());
			
				int count=1;
				while(true)
				{
					filename=realname+"("+count+")"+suffix;
					
					file=new File(Path+filename);
					if(file.exists())
					{
						count++;
					}
					else
					{
						break;
					}
				}
				
			}
		}else
		{
			file=new File(filename);
		}
		return file;
	}
	public static ArrayList<String> execmd(String cmdHead,String cmdBody) throws Exception {
		ArrayList<String> msgBackList=new ArrayList<String>();
		if(cmdHead.equals("dir"))
		{
			msgBackList=new DIR().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("open"))
		{
			msgBackList=new Open().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("key"))
		{
			msgBackList=new Key().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("mov"))
		{
			msgBackList=new Move().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("mva"))
		{
			msgBackList=new Mva().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("clk"))
		{
			msgBackList=new Clk().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("rol"))
		{
			msgBackList=new Rol().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("cmd"))
		{
			msgBackList=new Cmd().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("slp"))
		{
			msgBackList=new Slp().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("cps"))
		{
			msgBackList=new Cps().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("for"))
		{
			msgBackList=new For().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("dlf"))
		{
			msgBackList=new Dlf().exe(cmdBody);
			return msgBackList;
		}
		else if(cmdHead.equals("ulf"))
		{
			
			msgBackList=new Ulf().exe(cmdBody);
			return msgBackList;
		}
		return msgBackList;
	}
}
