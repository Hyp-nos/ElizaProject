package serverClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.ElizaView;

public class ElizaServer {
	
	ElizaView view;

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1342);
		while (true) {
			
			Socket ss = server.accept();

		User user = new User(ss);
		Thread thread = new Thread(user);
		thread.start();
	}
	

}
}