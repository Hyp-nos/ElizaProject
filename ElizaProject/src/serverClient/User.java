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
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.ElizaBrain;

public class User implements Runnable {
	Socket clientSocket;
	String messageFromClient = new String("");
	BufferedReader br;
	BufferedWriter pw;


	String[][] brain = new String[][]{
		// greetings  group0
		{ "hello", "hi", "yo", "what's up", "hi there", "hey" },
		{ "Hi", "Hello", "hey" },

		// asking about well being     group1
		{ "how are you", "how r you", "how r u", "how you doing", "how u doing", "are you ok" },
		{ "Good", "Great", "I'm doing ok thanks for asking" },
		
		// default response   group2
		{ "I'm not sure if I got that right", "I did not understand that", "can you rephrase that ?", "LoL","Eliza is offline"  }};

	public User(Socket ss) {
		clientSocket = ss;

	}

	@Override
	public void run() {
		try {

			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			while ((messageFromClient = br.readLine()) != null) {
				sendToClient();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sendToClient() {

		try {
			dealWithMessageFromClient(messageFromClient);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void dealWithMessageFromClient(String message) throws IOException {
		int group=0;
		System.out.println("level one reached");
		/*
		 * foundResponse 0: searching
		 *  1: no response 
		 *  2: found a response
		 */
		int foundResponse = 0;
		
		// to remove potentional white spaces in the begining or in the end
		message = message.trim();
		while (message.charAt(message.length() - 1) == '!' || message.charAt(message.length() - 1) == '?'
				|| message.charAt(message.length() - 1) == '.'// || message.charAt(message.length()-1)==' '

		) {
			System.out.println("leve2 one reached");
			message = message.substring(0, message.length() - 1);}
			// in case there is a space that got created after deleted a
			// question mark or something
			message = message.trim();
			System.out.println("leve3 one reached");
			while (foundResponse==0){
				if (digIn(message.toLowerCase(), brain[group*2])){ // *2 cuz we only wanna check in the even number of index of the brain 
					System.out.println("leve4 one reached");
					foundResponse =2;
					// give a random answer from the group  |||| Credits for this line goes to JRY a github user and another post by UBIK LOAD PACK another user 
					int r= (int) Math.floor(Math.random()*brain[(group*2)+1].length);
					pw.write(brain[(group*2)+1][r]+"\n");   // r is the index of the array that we wanna choose a response from
					pw.flush();
					System.out.println("leve5 one reached");
				}
				group++;
				
				if(group*2== brain.length - 1 && foundResponse==0){ //since the responde is always an even number as i mentioned before
					foundResponse=1; System.out.println("leve6 one reached");
					}
				}
			// The defaul answer
				if(foundResponse==1){
					int r= (int) Math.floor(Math.random()*brain[brain.length-1].length);  // i made the default the last index and we should keep it this way even if i scale it up later
					pw.write(brain[brain.length-1][r]+"\n"); 
				}
				System.out.println("leve7 one reached");// just in case 
			pw.flush();	
		}
		
	
	
	public boolean digIn(String input, String[] target){  //search for a match
		
		boolean found=false;
		for (int i=0; i<target.length; i++){
			if(target[i].equals(input)){
				found = true;
			}
		}
	
		return found;
		
	}
}
