package huangyipan.client.data;

import huangyipan.client.operator.ShowRemoteFileHandler;
import huangyipan.client.socket.CmdClientSocket;
import android.app.Application;
import android.content.Context;

public class APPValue extends Application {
	private String ip;
	private int port;
	private ShowRemoteFileHandler handler;
	private Context context;
	 public ShowRemoteFileHandler getHandler() {
		return handler;
	}
	public void setHandler(ShowRemoteFileHandler handler) {
		this.handler = handler;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
