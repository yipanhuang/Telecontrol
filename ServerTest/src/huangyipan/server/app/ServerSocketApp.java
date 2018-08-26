package huangyipan.server.app;

import java.io.IOException;



import huangyipan.server.socket.CmdServerSocketThread;

public class ServerSocketApp {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		CmdServerSocketThread cmd=new CmdServerSocketThread();
		try {

			cmd.start();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
