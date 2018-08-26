package huangyipan.client.operator;

import java.util.ArrayList;

import huangyipan.client.data.HotKeyData;
import huangyipan.client.data.Image_HotKey;
import huangyipan.client.data.Movie_HotKey;
import huangyipan.client.data.Music_HotKey;
import huangyipan.client.data.NetFileData;
import huangyipan.client.data.Other_HotKey;
import huangyipan.client.data.PPT_HotKey;
import huangyipan.client.data.TXT_HotKey;

public class HotkeyGenerate {
	NetFileData item;
	ArrayList<HotKeyData> lists;
	public HotkeyGenerate(NetFileData item)
	{
		this.item=item;
		
	}
	public ArrayList<HotKeyData> JudgeType()
	{
		String typename=item.getFileType();
		if(typename.equals(PPT_HotKey.getTypeName()))
		{
			lists=PPT_HotKey.getHotKey();
		}
		else if(typename.equals(TXT_HotKey.getTypeName()))
		{
			lists=TXT_HotKey.getHotKey();
			
			
		}
		else if(Movie_HotKey.locate(typename))
		{
			lists=Movie_HotKey.getHotKey();
		}
		else if(Image_HotKey.locate(typename))
		{
			lists=Image_HotKey.getHotKey();
		}
		else if(Music_HotKey.locate(typename))
		{
			lists=Music_HotKey.getHotKey();
		}
		else
		{
			lists=Other_HotKey.getHotKey();
		}
		return lists;
	}
}
