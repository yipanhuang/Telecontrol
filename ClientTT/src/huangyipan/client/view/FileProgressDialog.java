package huangyipan.client.view;

import java.util.Stack;

import android.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class FileProgressDialog {
	private Handler handler;
	private String title;
	private ProgressDialog pg;
	private Context context;
	private int progressValue=0;
	
	public FileProgressDialog(final Context context,String title)
	{
		super();
		this.context=context;
		this.title=title;
		this.handler = new Handler() { 	  
		    @Override
		    public void handleMessage(Message msg) { 
		    	Bundle bundle=msg.getData();
		        progressValue= bundle.getInt("percent"); 
		        Log.e("进度", progressValue+"");
		        pg.setProgress(progressValue);//设置进度条当前值 
		        if (progressValue == 100) {//判读是否读取结束         
		          pg.dismiss();    
		          Toast.makeText(context, "读取完成！", Toast.LENGTH_SHORT).show(); 
		        } 
		      }
		  }; 
	}
	public void  showDialog()
	{
		pg=new ProgressDialog(context);
		pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
		pg.setCancelable(true);// 设置是否可以通过点击Back键取消
        pg.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        pg.setIcon(R.drawable.title_bar);// 设置提示的title的图标，默认是没有的
        pg.setTitle("提示");
        pg.setMax(100);
        pg.show();
	}
	public Handler getHandler()
	{
		return handler;
	}
}

