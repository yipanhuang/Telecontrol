package huangyipan.server.operator;

import huangyipan.server.event.VisualKeyMap;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;



public class Key implements BaseOperator{
	private Robot robot;
	public ArrayList<String> exe(String cmdBody) 
	{
		ArrayList<String> msgBackList=new ArrayList<String>();
		try {
			robot=new Robot();
			
			int splitIdx=cmdBody.indexOf(",");
			if(splitIdx<0)
			{
				int splitIdx2=cmdBody.indexOf("+");
				if(splitIdx2<0)
				{
					singleKeyPress(cmdBody);
				}else
				{
					simpleComboKeyPress(cmdBody);
				}	
			}
			else{		
				complexComboKeyPress(cmdBody);
			}
			msgBackList.add("OK");
			msgBackList.add("key:"+cmdBody);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
			return msgBackList;
		}
		
		
		return msgBackList;
	}
	private void complexComboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		String[] keyPressOrRelease=cmdBody.split(",");
		String[] keyPress=keyPressOrRelease[0].split("\\+");
		String[] keyRealease=keyPressOrRelease[1].split("\\+");
		for(int i=0;i<keyPress.length;i++)
		{
			int keycode=VisualKeyMap.getVisualKey(keyPress[i]);
			robot.keyPress(keycode);
		}
		for(int i=0;i<keyRealease.length;i++)
		{
			int keycode=VisualKeyMap.getVisualKey(keyRealease[i]);
			robot.keyRelease(keycode);
		}
	}
	private void simpleComboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		String[] keyPressArray=cmdBody.split("\\+");
		for(int i=0;i<keyPressArray.length;i++)
		{
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyPress(keycode);
		}
		for(int i=keyPressArray.length-1;i>=0;i--)
		{
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyRelease(keycode);
		}
	}
	private void singleKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		int keycode=VisualKeyMap.getVisualKey(cmdBody);
		robot.keyPress(keycode);
		robot.keyRelease(keycode);
	}
}
