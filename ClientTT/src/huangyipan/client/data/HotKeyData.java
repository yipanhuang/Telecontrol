package huangyipan.client.data;

public class HotKeyData {
	private String hotKeyName="";
	private String hotKeyCmd="";
	public HotKeyData(String hotKeyName,String hotKeyCmd)
	{
		this.hotKeyCmd=hotKeyCmd;
		this.hotKeyName=hotKeyName;
	}
	public String getHotKeyName() {
		return hotKeyName;
	}
	public void setHotKeyName(String hotKeyName) {
		this.hotKeyName = hotKeyName;
	}
	public String getHotKeyCmd() {
		return hotKeyCmd;
	}
	public void setHotKeyCmd(String hotKeyCmd) {
		this.hotKeyCmd = hotKeyCmd;
	}
}
