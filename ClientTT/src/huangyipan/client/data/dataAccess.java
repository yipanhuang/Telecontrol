package huangyipan.client.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class dataAccess {
	final String KEY_IP="key_ip";
	final String KEY_PORT="key_port";
	Context context;
	public dataAccess(Context context)
	{
		this.context=context;
	}
	public void saveData(IPDATA ipdata)
	{
		 SharedPreferences sp = context.getSharedPreferences(context.getClass().getCanonicalName(), Context.MODE_PRIVATE);
		 Editor editor=sp.edit();
		 editor.putString(KEY_IP,ipdata.IP );
		 editor.putInt(KEY_PORT,ipdata.PORT);
		 editor.commit();
	}
	public IPDATA loadData()
	{
		SharedPreferences sp=context.getSharedPreferences(context.getClass().getCanonicalName(), Context.MODE_PRIVATE);
		String ip=sp.getString(KEY_IP, "127.0.0.0");
		int port=sp.getInt(KEY_PORT, 8019);
		IPDATA ipdata=new IPDATA(ip,port);
		return ipdata;
	}
}
