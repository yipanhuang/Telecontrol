package huangyipan.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TXT_HotKey {
	private static String typeName="txt";
	private static ArrayList<HotKeyData> hotkey;
	public TXT_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("���", "VK_WINDOWS+VK_UP"));
		hotkey.add(new HotKeyData("��С��", "VK_WINDOWS+VK_DOWN"));
		hotkey.add(new HotKeyData("����","VK_CONTROL+VK_S"));
		
		
	}
	public static String getTypeName() {
		return typeName;
	}
	public static void setTypeName(String typeName) {
		TXT_HotKey.typeName = typeName;
	}
	public static ArrayList<HotKeyData> getHotKey()
	{
		return hotkey;
	}
}
