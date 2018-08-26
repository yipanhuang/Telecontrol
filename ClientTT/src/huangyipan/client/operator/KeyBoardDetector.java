package huangyipan.client.operator;

import java.io.IOException;

import huangyipan.client.socket.CmdClientSocket;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

public class KeyBoardDetector extends SimpleOnGestureListener {
	private String ip;
	private int port;
	private ShowRemoteFileHandler handler;
	private Context context;
	CmdClientSocket cmd;
	public KeyBoardDetector(String ip,int port,ShowRemoteFileHandler handler,Context context,CmdClientSocket cmd)
	{
		this.ip=ip;
		this.port=port;
		this.handler=handler;
		this.context=context;
		this.cmd=cmd;
	}
	@Override
	public boolean onDoubleTap(MotionEvent e) {      //˫������
		// TODO Auto-generated method stub
		try {
			cmd=new CmdClientSocket(ip, port, handler, context);
			cmd.work("clk:double");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
//	@Override
//	public boolean onDown(MotionEvent e) {         //down�¼�,���˳�����֮���϶�֮��ĵ����������ͷţ��Ͳ��ܵ���onscroll������
//		// TODO Auto-generated method stub
//		//Log.e("x����", ""+e.getX());
//		//CmdClientSocket cmd=new CmdClientSocket(ip, port, handler, context);
//		//cmd.work("clk:left");
////		try {
////			cmd.longwriteBackMsg("clk:left");
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
//		try {
//			cmd.longwriteBackMsg("clk:left_press");
//			
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return true;
//	}
//	
//	@Override
//	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//			float velocityY) {
//		// TODO Auto-generated method stub
//		
//		
//		String cmdbody="mov:"+(int)(-velocityX)+","+(int)(-velocityY);
//		
//		
//		try {
//			CmdClientSocket cmd=new CmdClientSocket(ip, port, handler, context);
//			cmd.work(cmdbody);
//			//cmd.longwriteBackMsg(cmdbody);
//			
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return true;
//	}

	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {                 //����Ļ���϶��¼�
		// TODO Auto-generated method stub
		String cmdbody="mov:"+(int)(-distanceX)+","+(int)(-distanceY);
		try {
			cmd=new CmdClientSocket(ip, port, handler, context);
			cmd.work(cmdbody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
