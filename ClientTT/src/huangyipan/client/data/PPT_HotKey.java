package huangyipan.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PPT_HotKey {
	private static String typeName="ppt";
	public static String getTypeName() {
		return typeName;
	}
	public static void setTypeName(String typeName) {
		PPT_HotKey.typeName = typeName;
	}
	private static ArrayList<HotKeyData> hotkey;
	public PPT_HotKey()
	{
		hotkey=new ArrayList<HotKeyData>();
		hotkey.add(new HotKeyData("�л�����", "VK_ALT+VK_ESCAPE"));
		hotkey.add(new HotKeyData("ESC", "VK_ALT+VK_F4"));
		hotkey.add(new HotKeyData("��һҳ", "VK_P"));
		hotkey.add(new HotKeyData("��һҳ", "VK_N"));
		hotkey.add(new HotKeyData("��ͷ��ӳ", "VK_F5"));
		hotkey.add(new HotKeyData("��ǰ��ӳ", "VK_SHIFT+VK_F5"));
		hotkey.add(new HotKeyData("����/����", "VK_B"));
		hotkey.add(new HotKeyData("����/����", "VK_W"));
		
		
	}
	public static ArrayList<HotKeyData> getHotKey()
	{
		return hotkey;
	}
}
