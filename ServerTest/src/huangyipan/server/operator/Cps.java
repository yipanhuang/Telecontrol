package huangyipan.server.operator;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.awt.datatransfer.Clipboard;


public class Cps implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try{
		java.awt.datatransfer.Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable Text=new StringSelection(cmdBody);
		clip.setContents(Text, null);;
		msgBackList.add("OK");
		msgBackList.add("cps:"+cmdBody);
		}
		catch(Exception e){
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		return msgBackList;
	}

}
