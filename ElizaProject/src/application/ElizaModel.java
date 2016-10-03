package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ElizaModel implements Runnable{
	ElizaView view;
	Socket s;
	BufferedWriter pw;
	BufferedReader br;
	 String response ;
	public ElizaModel(ElizaView view) {
		this.view = view;

		try {

			s = new Socket("127.0.0.1", 40009);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void sendToServer() {
		System.out.println("reached sent to server method");
		String userinputString = "...";
		try {
			userinputString = view.txtInput.getText();
		System.out.println(userinputString); //testing
		pw.write(userinputString);
			
			view.txtArea.appendText("> You : " + userinputString + "\n");
			pw.flush();
			
			updateTxtAread();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTxtAread() {
		try {
			//while((response=br.readLine() )!=null){
			response=br.readLine();
				view.txtArea.appendText(response);
				System.out.println(response);
			
			view.txtArea.appendText("from Server" + response);//}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		sendToServer();
		
	}

}