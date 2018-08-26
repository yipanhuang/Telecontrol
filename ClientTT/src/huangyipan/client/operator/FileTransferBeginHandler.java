package huangyipan.client.operator;

import java.io.File;

import huangyipan.client.data.NetFileData;
import huangyipan.client.socket.FileDownLoadSocketThread;
import huangyipan.client.view.FileProgressDialog;
import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class FileTransferBeginHandler extends Handler{
	private NetFileData fileData;
	private Context context;
	private String ip;
	public FileTransferBeginHandler(Context context,NetFileData fileData,String ip)
	{
		super();
		this.fileData=fileData;
		this.context=context;
		this.ip=ip;
	}
	private File checkFile(String dictory,String filename)
	{
		File file=new File(dictory+filename);
		System.out.println(dictory+".."+filename);
		if(file.exists())
		{
			int location=filename.lastIndexOf(".");
			String realname=filename.substring(0,location);
			String suffix=filename.substring(location,filename.length());
			System.out.println(realname+".."+suffix);
			int count=1;
			while(true)
			{
				filename=realname+"("+count+")"+suffix;
				file=new File(dictory+filename);
				if(file.exists())
				{
					count++;
				}
				else
				{
					break;
				}
			}
		}
		return file;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Bundle bundle=msg.getData();
		
		int port= Integer.parseInt(bundle.getString("port"));
		int size=Integer.parseInt(bundle.getString("size"));
		String dictory=CheckLocalDownLoadFolder.check();
		Toast.makeText(context, dictory, Toast.LENGTH_SHORT).show();
		FileProgressDialog progress=new FileProgressDialog(context, "ÏÂÔØ½ø¶È");
		progress.showDialog();
		String filename=fileData.getFileName();
		File file=checkFile(dictory, filename);
		FileDownLoadSocketThread fileDownLoad=new FileDownLoadSocketThread(ip, port,progress.getHandler(), size,file);
		try {
			fileDownLoad.transferFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Toast.makeText(context, port+"¹þ¹þ"+size, Toast.LENGTH_SHORT).show();
	}
}
