package huangyipan.server.operator;

import java.awt.Robot;
import java.util.ArrayList;

public class Rol implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try{
			Robot robot=new Robot();
			int value=Integer.parseInt(cmdBody);
			robot.mouseWheel(value);
			msgBackList.add("OK");
			msgBackList.add("rol:"+cmdBody);
		}
		catch(Exception e)
		{
				msgBackList.add("FAIL");
				msgBackList.add(e.toString());
		}
				
		return msgBackList;
	}

}
