package huangyipan.client.data;

import java.util.ArrayList;

public class Other_HotKey {
	
	
	
	private static ArrayList<HotKeyData> hotkey;
	public Other_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("���", "VK_WINDOWS+VK_UP"));
		hotkey.add(new HotKeyData("��С��", "VK_WINDOWS+VK_DOWN"));
		
	}
	public static ArrayList<HotKeyData> getHotKey()
	{
		return hotkey;
	}
	
}
