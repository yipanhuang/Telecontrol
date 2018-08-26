package huangyipan.server.socket;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class FileSocketThread extends Thread{
	protected ServerSocket serverSocket;
	protected long filePos= 01;
	protected File file;
	protected int port=8018;
	protected Socket socket;
	abstract public void Task();
}
