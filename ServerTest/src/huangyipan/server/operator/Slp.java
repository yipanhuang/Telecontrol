package huangyipan.server.operator;

import java.util.ArrayList;

public class Slp implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try {
			Thread.sleep(Integer.parseInt(cmdBody));
			msgBackList.add("OK");
			msgBackList.add("slp:"+cmdBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		return msgBackList;
	}

}
