package serverClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.ElizaView;

public class ElizaServer {

	ElizaView view;
	Socket ss;

	public static void main(String[] args) throws Exception {
		ElizaServer server = new ElizaServer();

		try {
			server.run();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private void run() throws Exception {
		ServerSocket serverSocket = new ServerSocket(1342);
		
		System.out.println("server is connecting ....");
		while (true) {

			ss = serverSocket.accept();
			createThread();
		}
		

	}

	private void createThread() {
		User thread = new User(ss);
		thread.start();
		
	}

}