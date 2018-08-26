package huangyipan.client.app;

import java.io.IOException;

import huangyipan.client.data.APPValue;
import huangyipan.client.operator.KeyBoardDetector;
import huangyipan.client.operator.LongTouchButton;
import huangyipan.client.operator.LongTouchButton.LongTouchListener;

import huangyipan.client.operator.ShowRemoteFileHandler;
import huangyipan.client.socket.CmdClientSocket;

import com.example.clienttt.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RadioGroup;

import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TouchActivity extends Activity {

	TextView tv_touch;
	Button bt_left;
	Button bt_right;
	LongTouchButton bt_scrollup;
	LongTouchButton bt_scrolldown;
	ToggleButton tgb_leftnorelease;
	CmdClientSocket cmd;
	String ip;
	int port;
	boolean leftsign=true;
	Context context;
	ShowRemoteFileHandler handler;
	private GestureDetector mGestureDetector;
	protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.touchmain);
        
        APPValue appValue=(APPValue) getApplication();
        ip=appValue.getIp();
        port=appValue.getPort();
        handler=appValue.getHandler();
        
        context=appValue.getContext();
        cmd=new CmdClientSocket(ip, port, handler, context);
        //cmd.longconnect();
        mGestureDetector=new GestureDetector(this,
        		new KeyBoardDetector(ip,port,handler,context,cmd));
        Init();
	}
	private void Init() {
		// TODO Auto-generated method stub
		tv_touch=(TextView) findViewById(R.id.tv_touch);
		bt_left=(Button) findViewById(R.id.bt_left);
		bt_right=(Button) findViewById(R.id.bt_right);

		bt_scrollup=(LongTouchButton) findViewById(R.id.bt_scrollup);
		bt_scrolldown=(LongTouchButton) findViewById(R.id.bt_scrolldown);

		tgb_leftnorelease=(ToggleButton) findViewById(R.id.tgb_leftnorelease);
		bt_scrollup.setOnLongTouchListener(new LongTouchListener() {
			
			@Override
			public void onLongTouch() {
				// TODO Auto-generated method stub
				try {
					cmd.work("rol:-3");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 1000);
		bt_scrolldown.setOnLongTouchListener(new LongTouchListener(){
			@Override
			public void onLongTouch(){	
				try {
					//cmd.longwriteBackMsg("rol:3");
					//CmdClientSocket cmd=new CmdClientSocket(ip, port, handler, context);
					cmd.work("rol:3");
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 1000);
		bt_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					//CmdClientSocket cmd=new CmdClientSocket(ip, port, handler, context);
					cmd.work("clk:left");
					//cmd.longwriteBackMsg("clk:left");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bt_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					//CmdClientSocket cmd=new CmdClientSocket(ip, port, handler, context);
					cmd.work("clk:right");
					//cmd.longwriteBackMsg("clk:right");
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		tgb_leftnorelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{	
					try {
						cmd.work("clk:left_press");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					leftsign=!leftsign;
				}else
				{
					try {
						cmd.work("clk:left_release");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					leftsign=!leftsign;
				}
			}
		});
		tv_touch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mGestureDetector.onTouchEvent(event);
				return true;
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		try {
			
			cmd.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onDestroy();
	}
}
