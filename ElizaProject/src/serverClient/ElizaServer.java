package serverClient;


import java.net.ServerSocket;
import java.net.Socket;



public class ElizaServer {

	public static void main(String[] args) throws Exception {

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(40009);

		System.out.println("server is connecting ....");
		
		while (true) {
		Socket	ss = serverSocket.accept();
			User user = new User(ss);
			Thread th = new Thread(user);
			th.start();
			System.out.println("a client is connected");

		}

	}
}