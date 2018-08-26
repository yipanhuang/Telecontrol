package huangyipan.client.view;

import java.util.ArrayList;
import java.util.List;

import com.example.clienttt.R;

import huangyipan.client.data.HotKeyData;
import huangyipan.client.socket.CmdClientSocket;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HotKeyGridAdapter extends ArrayAdapter<HotKeyData> {

	private Context context;
	private ArrayList<HotKeyData> list;
	private CmdClientSocket cmdClientSocket;
	
	public HotKeyGridAdapter(Context context,ArrayList<HotKeyData> list,CmdClientSocket cmdClientSocket) {
		super(context, android.R.layout.simple_expandable_list_item_1);
		this.context=context;
		this.list=list;
		this.cmdClientSocket=cmdClientSocket;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public HotKeyData getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;
		if(convertView!=null)
		{
			v=convertView;
		}else
		{
			 v=LayoutInflater.from(context).inflate(R.layout.griditem, null);
			
		}
		String hotkeyname=list.get(position).getHotKeyName();
		TextView hotkey=(TextView) v.findViewById(R.id.hotkey);
		hotkey.setText(hotkeyname);
		return v;
	}
}
