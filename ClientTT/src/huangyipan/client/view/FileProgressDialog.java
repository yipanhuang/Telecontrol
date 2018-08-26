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
		        Log.e("����", progressValue+"");
		        pg.setProgress(progressValue);//���ý�������ǰֵ 
		        if (progressValue == 100) {//�ж��Ƿ��ȡ����         
		          pg.dismiss();    
		          Toast.makeText(context, "��ȡ��ɣ�", Toast.LENGTH_SHORT).show(); 
		        } 
		      }
		  }; 
	}
	public void  showDialog()
	{
		pg=new ProgressDialog(context);
		pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// ����ˮƽ������
		pg.setCancelable(true);// �����Ƿ����ͨ�����Back��ȡ��
        pg.setCanceledOnTouchOutside(false);// �����ڵ��Dialog���Ƿ�ȡ��Dialog������
        pg.setIcon(R.drawable.title_bar);// ������ʾ��title��ͼ�꣬Ĭ����û�е�
        pg.setTitle("��ʾ");
        pg.setMax(100);
        pg.show();
	}
	public Handler getHandler()
	{
		return handler;
	}
}

