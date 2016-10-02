package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ElizaModel {
	ElizaView view;
	Socket s;
	PrintWriter pw;
	BufferedReader br;

	public ElizaModel(ElizaView view) {
		this.view = view;

		try {

			s = new Socket("127.0.0.1", 1342);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void sendToServer() {
		System.out.println("reached sent to server method");
		String userinputString = null;
		try {
			userinputString = view.txtInput.getText();
			view.txtArea.appendText("> You : " + userinputString + "\n");

			pw.println(userinputString);
			updateTxtAread();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTxtAread() {
		try {
			String response = br.readLine();
			view.txtArea.appendText("from Server" + response);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}