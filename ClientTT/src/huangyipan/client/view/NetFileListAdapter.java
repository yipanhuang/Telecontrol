package huangyipan.client.view;

import java.util.ArrayList;
import java.util.List;

import com.example.clienttt.R;

import huangyipan.client.data.NetFileData;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NetFileListAdapter extends ArrayAdapter<NetFileData> {

	private Context context;
	ArrayList<NetFileData> netfiledata;
	public NetFileListAdapter(Context context,
			ArrayList<NetFileData> objects) {
		super(context,android.R.layout.simple_expandable_list_item_1, objects);
		this.context=context;
		this.netfiledata=objects;
		
		// TODO Auto-generated constructor stub
	}
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	View v;
	if(convertView!=null)
	{
		v=convertView;
	}
	else
	{
    v=LayoutInflater.from(context).inflate(R.layout.item, null);
	}
    ImageView logo=(ImageView) v.findViewById(R.id.logo);
    TextView fileName,fileDate,fileSize;
    fileName=(TextView) v.findViewById(R.id.fileName);
    fileDate=(TextView) v.findViewById(R.id.fileDate);
    fileSize=(TextView) v.findViewById(R.id.fileSize);
    fileName.setText(netfiledata.get(position).getFileName());
    fileDate.setText(netfiledata.get(position).getFileModeifiedDate());
    fileSize.setText(netfiledata.get(position).getFileSizeStr());
   
    if(netfiledata.get(position).isDisk())
    {
    	logo.setImageResource(R.drawable.disk);
    	 fileSize.setText("");
    }
    else if(netfiledata.get(position).isDireatory())
    {
    	logo.setImageResource(R.drawable.folder);
    	fileSize.setText("");
    }
    else
    	logo.setImageResource(R.drawable.file);
	return v;
}
}
