package serverClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.text.View;

import application.ElizaView;

public class ElizaServer {
	ElizaView view;

	public static void main(String[] args)  throws IOException {
		
		
		ServerSocket server= new ServerSocket(1342);
		Socket ss= server.accept(); // nothing after this get excuted till a client connects, it runs forever no timeout 
		//System.out.println("a client connected");
		
		DataInputStream din = new DataInputStream(ss.getInputStream());
		DataOutputStream dout = new DataOutputStream(ss.getOutputStream());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String  message ="", response="";
		while (!message.equals("bye")){
			message = din.readUTF();
			System.out.println(message);
			response = br.readLine();
			dout.writeUTF(response);
			dout.flush();
		
		}
		ss.close();}
	 

}
