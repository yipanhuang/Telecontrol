package huangyipan.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Move implements BaseOperator{
	public ArrayList<String> exe(String cmdBody)
	{
		ArrayList<String> msgBackList=new ArrayList<String>();
		double x=0,y=0;
		try {
			
			String axis[]=cmdBody.split(",");
			x=Double.parseDouble(axis[0]);
			y=Double.parseDouble(axis[1]);
			Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
			int width=(int) dimension.getWidth();
			int height=(int) dimension.getHeight();
			Robot robot=new Robot();
			Point point=MouseInfo.getPointerInfo().getLocation();
			x=point.x+x;
			y=point.y+y;
			if(x>width)
			{
				x=width;
			}
			else if(x<0)
			{
				x=0;
			}
			if(y>height)
			{
				y=height;
			}
			else if(y<0)
			{
				y=0;
			}
			robot.mouseMove((int)x,(int)y);
			msgBackList.add("OK");
			msgBackList.add("mov:"+x+","+y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			
			msgBackList.add("FAIL");
			msgBackList.add(e.toString());
			return msgBackList;
		}
		
		
		return msgBackList;
	}

}
