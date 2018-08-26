package huangyipan.client.data;

import java.util.ArrayList;

public class Music_HotKey {
	private static ArrayList<String> typeName;
	public static ArrayList<String> getTypeName() {
		return typeName;
	}
	
	private static ArrayList<HotKeyData> hotkey;
	public Music_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("���", "VK_WINDOWS+VK_UP"));
		hotkey.add(new HotKeyData("��С��", "VK_WINDOWS+VK_DOWN"));
		hotkey.add(new HotKeyData("����/��ͣ", "VK_CONTROL+VK_P"));
		
		hotkey.add(new HotKeyData("��һ�׸�", "VK_CONTROL+VK_RIGHT"));
		hotkey.add(new HotKeyData("��һ�׸�", "VK_CONTROL+VK_LEFT"));
		hotkey.add(new HotKeyData("���", "VK_CONTROL+VK_SHIFT+VK_RIGHT"));
		hotkey.add(new HotKeyData("����", "VK_CONTROL+VK_SHIFT+VK_LEFT"));
		hotkey.add(new HotKeyData("��������", "VK_CONTROL+VK_UP"));
		hotkey.add(new HotKeyData("��С����", "VK_CONTROL+VK_DOWN"));
		hotkey.add(new HotKeyData("����", "VK_CONTROL+VK_M"));
		hotkey.add(new HotKeyData("��ʾ/���ظ��", "VK_CONTROL+VK_ALT+VK_D"));
		
		
		typeName=new ArrayList<String>();
		
		typeName.add("mp3");
		typeName.add("cd");
		typeName.add("ogg");
		typeName.add("wav");
		typeName.add("mp3pro");
		typeName.add("real");
		typeName.add("ape");
		typeName.add("midi");
		typeName.add("vqf");
		typeName.add("flac");
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
