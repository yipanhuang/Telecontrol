package huangyipan.client.data;

public class IPDATA {
	String IP;
	int PORT;
	public IPDATA(String IP,int PORT)
	{
		this.IP=IP;
		this.PORT=PORT;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public int getPORT() {
		return PORT;
	}
	public void setPORT(int pORT) {
		PORT = pORT;
	}
	
}
