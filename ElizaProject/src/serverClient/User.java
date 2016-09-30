package serverClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User implements Runnable {
	Socket clientSocket;
	DataInputStream din;
	DataOutputStream dout;

	public User(Socket ss) {
		clientSocket = ss;
	}

	@Override
	public void run() {
		try {
			
			din = new DataInputStream(clientSocket.getInputStream());
		    dout = new DataOutputStream(clientSocket.getOutputStream());
		    System.out.println("reached here");
			sendToClient();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sendToClient() {
		String s = "let us try this wtf";
		try {
			dout.writeUTF(s);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
