package huangyipan.server.operator;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Clk implements BaseOperator{

	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		ArrayList<String> msgBackList=new ArrayList<String>();
		try{
			Robot robot = new Robot();
			if(cmdBody.equals("left"))
			{
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			}
			else if(cmdBody.equals("right"))
			{			
				robot.mousePress(KeyEvent.BUTTON3_MASK);
				robot.mouseRelease(KeyEvent.BUTTON3_MASK);
			}else if(cmdBody.equals("double"))
			{
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			}	
			else if(cmdBody.equals("left_press"))
			{
				robot.mousePress(KeyEvent.BUTTON1_MASK);
			}
			else if(cmdBody.equals("left_release"))
			{
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			}
			else if(cmdBody.equals("right_press"))
			{
				robot.mousePress(KeyEvent.BUTTON3_MASK);
			}
			else if(cmdBody.equals("right_release"))
			{
				robot.mouseRelease(KeyEvent.BUTTON3_MASK);
			}
			msgBackList.add("OK");
			msgBackList.add("clk:"+cmdBody);
		}
		catch(Exception e)
		{
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
		}
		
		return msgBackList;
	}

}
