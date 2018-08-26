package huangyipan.client.data;

import java.util.ArrayList;

public class Image_HotKey {
	private static ArrayList<String> typeName;
	public static ArrayList<String> getTypeName() {
		return typeName;
	}
	
	private static ArrayList<HotKeyData> hotkey;
	public Image_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("���", "VK_WINDOWS+VK_UP"));
		hotkey.add(new HotKeyData("��С��", "VK_WINDOWS+VK_DOWN"));
		
		hotkey.add(new HotKeyData("��ת", "VK_CONTROL+VK_R"));
		hotkey.add(new HotKeyData("�õ�Ƭ��ӳ", "VK_F5"));
		hotkey.add(new HotKeyData("��һ��ͼƬ", "VK_RIGHT"));
		hotkey.add(new HotKeyData("��һ��ͼƬ", "VK_LEFT"));
		hotkey.add(new HotKeyData("�Ŵ�/��С", "VK_CONTROL+VK_0"));
		
		typeName=new ArrayList<String>();
		
		typeName.add("bmp");
		typeName.add("jpg");
		typeName.add("jpeg");
		typeName.add("png");
		typeName.add("gif");
		
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
