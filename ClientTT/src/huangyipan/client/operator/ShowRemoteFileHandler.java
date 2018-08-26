package huangyipan.client.operator;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import huangyipan.client.data.NetFileData;
import huangyipan.client.socket.CmdClientSocket;
import huangyipan.client.view.NetFileListAdapter;

public class ShowRemoteFileHandler extends Handler {
	private Context context;
	private ListView listview;
	private NetFileListAdapter adapter;
	public ShowRemoteFileHandler(Context context,ListView listView)
	{
		super();
		this.context=context;
		this.listview=listView;
		
	}
	public NetFileListAdapter getAdapter()
	{
		return this.adapter;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Bundle bundle=msg.getData();
		final ArrayList<NetFileData> msgList = bundle.getParcelableArrayList("list");  
		adapter=new NetFileListAdapter(context, msgList);
		listview.setAdapter(adapter);
		
	}
}
