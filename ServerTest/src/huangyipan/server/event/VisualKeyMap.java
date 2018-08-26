package huangyipan.server.event;

import java.awt.event.InputEvent;
import java.util.HashMap;

import com.sun.glass.events.KeyEvent;

public class VisualKeyMap {
	private static HashMap<String,Integer> hashMap=new HashMap<String,Integer>();
	private static final VisualKeyMap VISUAL_KEY_MAP=new VisualKeyMap();
	private VisualKeyMap()
	{
		hashMap.put("VK_0", KeyEvent.VK_0);
		hashMap.put("VK_1", KeyEvent.VK_1);
		hashMap.put("VK_2", KeyEvent.VK_2);
		hashMap.put("VK_3", KeyEvent.VK_3);
		hashMap.put("VK_4", KeyEvent.VK_4);
		hashMap.put("VK_5", KeyEvent.VK_5);
		hashMap.put("VK_6", KeyEvent.VK_6);
		hashMap.put("VK_7", KeyEvent.VK_7);
		hashMap.put("VK_8", KeyEvent.VK_8);
		hashMap.put("VK_9", KeyEvent.VK_9);
		hashMap.put("VK_A", KeyEvent.VK_A);
		hashMap.put("VK_B", KeyEvent.VK_B);
		hashMap.put("VK_C", KeyEvent.VK_C);
		hashMap.put("VK_D", KeyEvent.VK_D);
		hashMap.put("VK_E", KeyEvent.VK_E);
		hashMap.put("VK_F", KeyEvent.VK_F);
		hashMap.put("VK_G", KeyEvent.VK_G);
		hashMap.put("VK_H", KeyEvent.VK_H);
		hashMap.put("VK_I", KeyEvent.VK_I);
		hashMap.put("VK_J", KeyEvent.VK_J);
		hashMap.put("VK_K", KeyEvent.VK_K);
		hashMap.put("VK_L", KeyEvent.VK_L);
		hashMap.put("VK_M", KeyEvent.VK_M);
		hashMap.put("VK_N", KeyEvent.VK_N);
		hashMap.put("VK_O", KeyEvent.VK_O);
		hashMap.put("VK_P", KeyEvent.VK_P);
		hashMap.put("VK_Q", KeyEvent.VK_Q);
		hashMap.put("VK_R", KeyEvent.VK_R);
		hashMap.put("VK_S", KeyEvent.VK_S);
		hashMap.put("VK_T", KeyEvent.VK_T);
		hashMap.put("VK_U", KeyEvent.VK_U);
		hashMap.put("VK_V", KeyEvent.VK_V);
		hashMap.put("VK_W", KeyEvent.VK_W);
		hashMap.put("VK_X", KeyEvent.VK_X);
		hashMap.put("VK_Y", KeyEvent.VK_Y);
		hashMap.put("VK_Z", KeyEvent.VK_Z);
		hashMap.put("VK_CTRL", KeyEvent.VK_CONTROL);
		hashMap.put("VK_ENTER", KeyEvent.VK_ENTER);
		hashMap.put("VK_ESCAPE", KeyEvent.VK_ESCAPE);
		hashMap.put("VK_ALT", KeyEvent.VK_ALT);
		hashMap.put("VK_SHIFT", KeyEvent.VK_SHIFT);
		hashMap.put("VK_CONTROL", KeyEvent.VK_CONTROL);
		hashMap.put("VK_TAB", KeyEvent.VK_TAB);
		hashMap.put("VK_WINDOWS", KeyEvent.VK_WINDOWS);
		hashMap.put("VK_SPACE", KeyEvent.VK_SPACE);
		hashMap.put("VK_BACK_SPACE", KeyEvent.VK_BACKSPACE);
		hashMap.put("VK_SLASH", KeyEvent.VK_SLASH);
		hashMap.put("VK_EQUALS", KeyEvent.VK_EQUALS);
		hashMap.put("VK_DOWN", KeyEvent.VK_DOWN);
		hashMap.put("VK_LEFT", KeyEvent.VK_LEFT);
		hashMap.put("VK_UP", KeyEvent.VK_UP);
		hashMap.put("VK_RIGHT", KeyEvent.VK_RIGHT);
		hashMap.put("VK_END", KeyEvent.VK_END);
		hashMap.put("VK_HOME", KeyEvent.VK_HOME);
		hashMap.put("VK_INSERT", KeyEvent.VK_INSERT);
		hashMap.put("VK_DELETE", KeyEvent.VK_DELETE);
		hashMap.put("VK_PAGE_DOWN", KeyEvent.VK_PAGE_DOWN);
		hashMap.put("VK_PAGE_UP", KeyEvent.VK_PAGE_UP);
		hashMap.put("VK_MINUS", KeyEvent.VK_MINUS);
		hashMap.put("VK_PLUS", KeyEvent.VK_PLUS);
		hashMap.put("VK_MULTIPLY", KeyEvent.VK_MULTIPLY);
		hashMap.put("VK_DIVIDE", KeyEvent.VK_DIVIDE);
		hashMap.put("VK_NUM_LOCK", KeyEvent.VK_NUM_LOCK);
		hashMap.put("VK_F1", KeyEvent.VK_F1);
		hashMap.put("VK_F2", KeyEvent.VK_F2);
		hashMap.put("VK_F3", KeyEvent.VK_F3);
		hashMap.put("VK_F4", KeyEvent.VK_F4);
		hashMap.put("VK_F5", KeyEvent.VK_F5);
		hashMap.put("VK_F6", KeyEvent.VK_F6);
		hashMap.put("VK_F7", KeyEvent.VK_F7);
		hashMap.put("VK_F8", KeyEvent.VK_F8);
		hashMap.put("VK_F9", KeyEvent.VK_F9);
		hashMap.put("VK_F10", KeyEvent.VK_F10);
		hashMap.put("VK_F11", KeyEvent.VK_F11);
		hashMap.put("VK_F12", KeyEvent.VK_F12);

		
	}
	public static int getVisualKey(String key)
	{
		return hashMap.get(key);
	}
}
