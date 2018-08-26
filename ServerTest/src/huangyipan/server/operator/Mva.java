package huangyipan.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Mva implements BaseOperator{
	ArrayList<String> msgBackList=new ArrayList<String>();
	public ArrayList<String> exe(String cmdBody) {
		// TODO Auto-generated method stub
		int abx = 0;
		int aby=0;
		try {
			String axis[]=cmdBody.split(",");
			double x=Double.parseDouble(axis[0]);
			double y=Double.parseDouble(axis[1]);
			Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
			int width=(int) dimension.getWidth();
			int height=(int) dimension.getHeight();
			Robot robot=new Robot();
			if(x<1&&x>=0&&y<1&&y>=0)
			{
				abx=(int) (x*width);
				aby=(int) (y*height);
				robot.mouseMove(abx, aby);
			}
			else if(x>=1||y>=1)
			{
				
				if(x>width)
				{
					abx=width;
				}
				else
				{
					abx=(int) x;
				}
				if(y>height)
				{
					aby=height;
				}
				else
				{
					aby=(int) y;
				}
				robot.mouseMove(abx, aby);
			}
			else
			{
				WrongMessage("输入的坐标不在屏幕范围内");
				return msgBackList;
			}
			msgBackList.add("OK");
			msgBackList.add("mva:"+abx+","+aby);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			WrongMessage(e.toString());
			return msgBackList;
		}
		
		
		return msgBackList;
	}
	public void WrongMessage(String error)
	{
		
		msgBackList.add("FAIL");
		msgBackList.add(error);
	}

}
