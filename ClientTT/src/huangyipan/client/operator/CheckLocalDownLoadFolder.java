package huangyipan.client.operator;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class CheckLocalDownLoadFolder {
	
	public static String check()
	{
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			return "此手机没有外置存储卡";
		}
		String SDpath=Environment.getExternalStorageDirectory().getPath()+"/DownLoad/";
		
		File file=new File(SDpath);
		
		if(!file.exists())
		{
			file.mkdir();
			return SDpath;
		}
		else
		{
			return SDpath;
		}
	}
}
