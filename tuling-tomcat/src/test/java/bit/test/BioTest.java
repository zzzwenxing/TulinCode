package bit.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioTest {

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(8080);
		while(true){
			final Socket so = socket.accept();
			new Thread(){
				public void run() {
//					so.getInputStream();
					
				};
			}.start();
		}
	}

}
