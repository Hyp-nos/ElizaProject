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
import application.ElizaView;

public class User implements Runnable {
	Socket clientSocket;
	String messageFromClient = new String("");
	BufferedReader br;
	BufferedWriter pw;
	String chatterName;


	String[][] brain = new String[][]{
		/*
		 * here i made groups of expected phrases and answers to them, the input from user has an odd index while response
		 * has even index
		 */
		
		// greetings  
		{ "hello", "hi", "yo", "what's up", "hi there", "hey" },
		{ "Hi", "Hello", "hey" },

		// asking about well being     
		{ "how are you", "how r you", "how r u", "how you doing", "how u doing", "are you ok","how are u", "how u doing" },
		{ "Good", "Great", "I'm doing ok thanks for asking" },
		
		// ansering good stuff
		{"good", "great", "amazing", "awesome"},
		{"I'm glad to hear that", "good to hear that", "are you sure ? ... just kidding"},
		
		// default response   
		{ "I'm not sure if I got that right", "I did not understand that", "can you rephrase that ?", "LoL","Eliza is offline"  }};

	public User(Socket ss) {
		clientSocket = ss;

	}

	@Override
	public void run() {
		try {
			
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		//	chatterName= br.readLine();
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

		/*
		 * foundResponse 0: searching
		 *  1: no response 
		 *  2: found a response
		 */
		int foundResponse = 0;
		
		// to remove potentional white spaces in the begining or in the end
		message = message.trim();
	/*	while (message.charAt(message.length() - 1) == '!' || message.charAt(message.length() - 1) == '?'
				|| message.charAt(message.length() - 1) == '.' || message.charAt(message.length()-1)==' '

		) {

			message = message.substring(0, message.length() - 1);}
			// in case there is a space that got created after deleted a
			// question mark or something
			message = message.trim();*/
		message = message.replaceAll("[\\p{P}\\s]+$", ""); // credits to this goes to Wiktor Strivizeiw in github 
		// http://stackoverflow.com/questions/33308203/regex-remove-all-punctuation-from-end-of-string

			while (foundResponse==0){
				if (digIn(message.toLowerCase(), brain[group*2])){ // *2 cuz we only wanna check in the even number of index of the brain 

					foundResponse =2;
					// give a random answer from the group  |||| Credits for this line goes to JRY a github user and another post by UBIK LOAD PACK another user 
					int r= (int) Math.floor(Math.random()*brain[(group*2)+1].length);
					pw.write(brain[(group*2)+1][r]+/*", "+chatterName+*/"\n");   // r is the index of the array that we wanna choose a response from
					pw.flush();

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

			pw.flush();	
			
		}
		
	
	
	public boolean digIn(String input, String[] target){  //search for a match
		
		boolean found=false;
		for (int i=0; i<target.length; i++){
			if(target[i].equalsIgnoreCase(input)){
				found = true;
			}
		}
	
		return found;
		
	}
}
