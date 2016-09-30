package serverClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User implements Runnable {
	Socket clientSocket;
	
	public User(Socket ss) {
		clientSocket=ss;
	}

	@Override
	public void run() {
		try {
		System.out.println("im in run");
		DataInputStream din = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
		
		
		String cliString =din.readUTF();
		String response = cliString+" this is from server";
		dout.writeUTF(response);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
