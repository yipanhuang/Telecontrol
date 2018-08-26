package huangyipan.client.view;

import java.util.ArrayList;

import com.example.clienttt.R;

import huangyipan.client.data.HotKeyData;
import huangyipan.client.socket.CmdClientSocket;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class HotKeyDialog {
	private Context context;
	private String title;
	private ArrayList<HotKeyData> hotKeyList;
	private CmdClientSocket cmdClientSocket;
	public HotKeyDialog(Context context,ArrayList<HotKeyData> hotKeyList,String title,CmdClientSocket cmdClientSocket)
	{
		this.context=context;
		this.title=title;
		this.hotKeyList=hotKeyList;
		this.cmdClientSocket=cmdClientSocket;
		
	}
	public void show()
	{
		final AlertDialog.Builder dialog=new AlertDialog.Builder(this.context);
		View v=LayoutInflater.from(this.context).inflate(R.layout.dialog, null);
		HotKeyGridAdapter adapter=new HotKeyGridAdapter(this.context, hotKeyList, cmdClientSocket);
		GridView gv=(GridView) v.findViewById(R.id.hotkeygrid);
		gv.setAdapter(adapter);
		dialog.setTitle(title);
		dialog.setView(v);
		dialog.setNegativeButton("ÍË³ö",new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				HotKeyData temp=(HotKeyData) parent.getItemAtPosition(position);
				cmdClientSocket.work("key:"+temp.getHotKeyCmd());	
			}
		});
		dialog.show();
	}
}
