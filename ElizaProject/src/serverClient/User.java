package serverClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class User  implements Runnable {
	Socket clientSocket;
	String messageFromClient = new String("");
	BufferedReader br;
	BufferedWriter pw;
	
	public User(Socket ss) {
		clientSocket = ss;

	}

	@Override
	public void run() {
		try {
			
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		
			while 
				((messageFromClient=br.readLine())!=null){
				 System.out.println("we are in while loop in user");
			System.out.println(br.readLine());
			sendToClient();
			
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sendToClient() {
		String s = "senToClient method";
		System.out.println(s);
		System.out.println(messageFromClient);
		try {
			if (messageFromClient.equals("hello")){
				pw.write("hey you!");
			}
			else pw.write("try again");
			pw.flush();
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
