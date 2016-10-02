package serverClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class User extends Thread implements Runnable {
	Socket clientSocket;
	String messageFromClient = null;
	BufferedReader br;
	PrintWriter pw;

	public User(Socket ss) {
		clientSocket = ss;

	}

	@Override
	public void run() {
		try {

			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
			if (br.readLine()!=null){
			sendToClient();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sendToClient() {
		String s = "senToClient method";
		try {
			if (br.readLine()=="hello"){
				pw.write("hey you!");
			}
			else pw.write("try again");
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
