package serverClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args)  throws UnknownHostException, IOException{
		
	
			Socket s = new Socket("127.0.0.1",1342);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String  message ="", response="";
			while (!message.equals("bye")){
				message = din.readUTF();
				System.out.println(message);
				response = br.readLine();
				dout.writeUTF(response);
				dout.flush();
			
			}
			s.close();}
			
			
			
			
		
		
	}
	
	
	


