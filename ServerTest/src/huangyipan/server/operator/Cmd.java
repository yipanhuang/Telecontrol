package huangyipan.server.operator;

import java.io.IOException;
import java.util.ArrayList;

public class Cmd implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try {
			Runtime.getRuntime().exec("cmd /c start "+cmdBody);
			msgBackList.add("OK");
			msgBackList.add("cmd:"+cmdBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		return msgBackList;
	}

}
