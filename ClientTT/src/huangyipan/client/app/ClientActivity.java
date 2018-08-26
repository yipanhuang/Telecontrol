package huangyipan.client.app;

import huangyipan.client.data.APPValue;
import huangyipan.client.data.HotKeyData;
import huangyipan.client.data.IPDATA;
import huangyipan.client.data.Image_HotKey;
import huangyipan.client.data.Movie_HotKey;
import huangyipan.client.data.Music_HotKey;
import huangyipan.client.data.NetFileData;
import huangyipan.client.data.Other_HotKey;
import huangyipan.client.data.PPT_HotKey;
import huangyipan.client.data.TXT_HotKey;
import huangyipan.client.data.dataAccess;
import huangyipan.client.operator.CheckLocalDownLoadFolder;
import huangyipan.client.operator.FileTransferBeginHandler;
import huangyipan.client.operator.FileUploadBeginHandler;
import huangyipan.client.operator.HotkeyGenerate;
import huangyipan.client.operator.ShowRemoteFileHandler;
import huangyipan.client.socket.CmdClientSocket;
import huangyipan.client.view.HotKeyDialog;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clienttt.R;

public class ClientActivity extends Activity{
	EditText ed_ip,ed_port;
	Button bt_conn,bt_revert;
	TextView tv_rece;
	ListView lv;
	String ip;
	int port;
	
	String choosePath;
	ShowRemoteFileHandler handler;
	FileTransferBeginHandler downloadhandler;
	FileUploadBeginHandler uploadhandler;
	CmdClientSocket cmd;
	Stack<String> pathLists=new Stack<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }
    
    private void init() {
		// TODO Auto-generated method stub
    	
    	lv=(ListView) findViewById(R.id.listView1);
		ed_ip=(EditText) findViewById(R.id.editText1);
		ed_port=(EditText) findViewById(R.id.editText2);
		bt_conn=(Button) findViewById(R.id.button1);
		tv_rece=(TextView) findViewById(R.id.textView1);
		bt_revert=(Button) findViewById(R.id.revert);
		dataAccess access=new dataAccess(ClientActivity.this);
		IPDATA ipdata=access.loadData();
		ed_ip.setText(ipdata.getIP());
		ed_port.setText(""+ipdata.getPORT());
		
		bt_conn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					ip=ed_ip.getText().toString();
					String p=ed_port.getText().toString();
					port=Integer.parseInt(p);
					cmd=new CmdClientSocket(ip, port, handler,ClientActivity.this);
					APPValue appvalue=(APPValue) getApplication();
					appvalue.setIp(ip);
					appvalue.setPort(port);
					appvalue.setHandler(handler);
					appvalue.setContext(ClientActivity.this);
					cmd.work("dir:...");
					pathLists.push("top");
				}
				catch(Exception e){
					Toast.makeText(ClientActivity.this,e.toString() , Toast.LENGTH_SHORT).show();
				}	
			}
		});
		bt_revert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler=new ShowRemoteFileHandler(ClientActivity.this, lv);
				cmd=new CmdClientSocket(ip, port, handler,ClientActivity.this);
				if(cmd==null)
				{
					Toast.makeText(ClientActivity.this, "还没有连接哦", Toast.LENGTH_SHORT).show();
					return;
				}
				
				
				String path=pathLists.pop();
				
				if(path.equals("top"))
				{
					pathLists.push("top");
					Toast.makeText(ClientActivity.this, "目前已经是最上级了哦", Toast.LENGTH_SHORT).show();
				}
				else if(path.equals("..."))
				{
					cmd.work("dir:...");
				}
				else
				{
					
					cmd.work("dir:"+path);
					
				}
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//handler.getAdapter().notifyDataSetChanged();
				cmd=new CmdClientSocket(ip, port, handler,ClientActivity.this);
				if(cmd==null)
				{
					Toast.makeText(ClientActivity.this, "还没有连接哦", Toast.LENGTH_SHORT).show();
					return;
				}
				NetFileData fileData=(NetFileData)parent.getItemAtPosition(position);
				String path=fileData.getFilePath();
				String name=fileData.getFileName();
				Log.e("序号", ""+position+"路径"+path+"文件名"+name);
				boolean isDireatory=fileData.isDireatory();
				boolean isDisk=fileData.isDisk();
				String filePath;
				filePath=fileData.getFileRealPath();
				Log.e("路径", filePath);
				if(!isDireatory&&!isDisk)
				{	
					
					cmd.work("open:"+filePath);
					
				}
				else
				{
					pathLists.push(path);
					cmd.work("dir:"+filePath);	
				}
				
			}

			
		});
		handler=new ShowRemoteFileHandler(ClientActivity.this, lv);
		new PPT_HotKey();
   	new TXT_HotKey();
   	new Movie_HotKey();
   	new Image_HotKey();
   	new Music_HotKey();
   	new Other_HotKey();
   	registerForContextMenu(lv);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.file_list_context_menu, menu);
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	AdapterContextMenuInfo contextMenuInfo=(AdapterContextMenuInfo) item.getMenuInfo();
    	int pos=contextMenuInfo.position;
    	NetFileData netFileData=(NetFileData) lv.getAdapter().getItem(pos);
    	Intent intent;
    	switch(item.getItemId())
    	{
    	case R.id.item1:
    		showHotKeyDialog(netFileData);
    		break;
    	case R.id.item2:
    		intent=new Intent(ClientActivity.this,TouchActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.item3:
    		if(netFileData.isDireatory()||netFileData.isDisk())
    		{
    			Toast.makeText(ClientActivity.this, "暂未实现整个文件夹下载功能", Toast.LENGTH_SHORT).show();
    		}
    		else{
    			downloadhandler=new FileTransferBeginHandler(this, netFileData,ip);
    			Toast.makeText(ClientActivity.this, netFileData.getFileRealPath(), Toast.LENGTH_SHORT).show();
				cmd=new CmdClientSocket(ip, port, downloadhandler, ClientActivity.this);
				cmd.work("dlf:"+netFileData.getFileRealPath());
    			
    		}
    		break;
    	case R.id.item4:
    		choosePath=netFileData.getFilePath()+"\\";
    		intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,1);
    		break;
    	default:
    			break;
    	}
    	return super.onContextItemSelected(item);
    }
    
	private void showHotKeyDialog(NetFileData netFileData) {

		// TODO Auto-generated method stub
		
		ArrayList<HotKeyData> lists;
		HotkeyGenerate hotkeyGenerate=new HotkeyGenerate(netFileData);
		lists=hotkeyGenerate.JudgeType();
		new HotKeyDialog(ClientActivity.this,lists , "热键操作", cmd).show();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		dataAccess access=new dataAccess(ClientActivity.this);
		String ip=ed_ip.getText().toString();
		String p=ed_port.getText().toString();
		int port=Integer.parseInt(p);
		access.saveData(new IPDATA(ip,port));
		super.onDestroy();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e("标记1", "0");

	    if (resultCode == Activity.RESULT_OK) {  
	        if (requestCode == 1) {  
	            Uri uri = data.getData();  
	            String filePath=uri.getPath().toString();  
	            System.out.println(filePath);
	            int location=filePath.lastIndexOf("/");
	            String fileName = null;
	            if(location!=-1){
	            fileName=filePath.substring(location+1,filePath.length());
	            }
	            File file=new File(filePath);
	            uploadhandler=new FileUploadBeginHandler(ClientActivity.this, ip,choosePath, filePath, fileName,file);
	            cmd=new CmdClientSocket(ip, port, uploadhandler, ClientActivity.this);
	            cmd.work("ulf:"+choosePath+fileName);
	           
	        }  
	    }  
	}
}
