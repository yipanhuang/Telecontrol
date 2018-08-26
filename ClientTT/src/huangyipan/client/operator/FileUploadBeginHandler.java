package huangyipan.client.operator;

import java.io.File;
import java.util.Stack;

import huangyipan.client.socket.FileUpLoadSocketThread;
import huangyipan.client.view.FileProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class FileUploadBeginHandler extends Handler{
	String filename;
	long alreadysize;
	long totalsize;
	String ip;
	int port;
	File file;
	Context context;

	public FileUploadBeginHandler(Context context,String ip,String serverPath,String clientPath,String fileName,File file) {
		// TODO Auto-generated constructor stub
		this.filename=fileName;
		this.totalsize=file.length();
		this.ip=ip;
		this.file=file;
		this.context=context;
	
	
		
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Bundle bundle=msg.getData();
		alreadysize=Integer.parseInt(bundle.getString("size"));
		port=Integer.parseInt(bundle.getString("port"));
		FileProgressDialog progress=new FileProgressDialog(context, "上传进度");
		progress.showDialog();
		Log.e("看看port", port+"&&"+totalsize);
		FileUpLoadSocketThread upload=new FileUpLoadSocketThread(ip, port, file, progress.getHandler(),totalsize,alreadysize);
		try {
			upload.transferFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
