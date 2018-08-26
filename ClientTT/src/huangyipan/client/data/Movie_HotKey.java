package huangyipan.client.data;

import java.util.ArrayList;

import android.R.bool;

public class Movie_HotKey {
	private static ArrayList<String> typeName;
	public static ArrayList<String> getTypeName() {
		return typeName;
	}
	
	private static ArrayList<HotKeyData> hotkey;
	public Movie_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("���", "VK_WINDOWS+VK_UP"));
		hotkey.add(new HotKeyData("��С��", "VK_WINDOWS+VK_DOWN"));
		hotkey.add(new HotKeyData("����/��ͣ", "VK_SPACE"));
		hotkey.add(new HotKeyData("��������", "VK_UP"));
		hotkey.add(new HotKeyData("��С����", "VK_DOWN"));
		hotkey.add(new HotKeyData("����", "VK_M"));
		hotkey.add(new HotKeyData("��һ����Ƶ", "VK_PAGE_UP"));
		hotkey.add(new HotKeyData("��һ����Ƶ", "VK_PAGE_DOWN"));
		hotkey.add(new HotKeyData("�ط�", "VK_RIGHT"));
		hotkey.add(new HotKeyData("���", "VK_LEFT"));
		
		typeName=new ArrayList<String>();
		
		typeName.add("mp4");
		typeName.add("avi");
		typeName.add("mov");
		typeName.add("mpeg");
		typeName.add("mpg");
		typeName.add("ram");
		typeName.add("RM");
		typeName.add("RMVB");
		typeName.add("RA");
		typeName.add("MKV");
	}
	public static ArrayList<HotKeyData> getHotKey()
	{
		return hotkey;
	}
	public static boolean locate(String str)
	{
		for(int i=0;i<typeName.size();i++)
		{
			if(typeName.get(i).equalsIgnoreCase(str))
			{
				return true;
			}
		}
		return false;
	}
}
