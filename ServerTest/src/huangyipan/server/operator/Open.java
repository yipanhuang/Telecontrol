package huangyipan.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Open implements BaseOperator{
	public  ArrayList<String> exe(String cmdBody)
	{
		ArrayList<String> msgBackList=new ArrayList<String>();
		
		
		try {
			Desktop desk=Desktop.getDesktop();
			File file=new File(cmdBody);
			desk.open(file);
			
			msgBackList.add("OK");
			msgBackList.add("opn:"+cmdBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		return msgBackList;
	}
}
