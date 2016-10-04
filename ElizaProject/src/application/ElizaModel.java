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

import javafx.application.Platform;

public class ElizaModel implements Runnable {
	ElizaView view;
	Socket s;
	BufferedWriter pw;
	BufferedReader br;
	String response;

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

		String userinputString = null;
		try {
			userinputString = view.txtInput.getText();
			pw.write(userinputString + "\n");
			view.txtArea.appendText("> You : " + userinputString + "\n");
			pw.flush();
			updateTxtArea();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTxtArea() {
		try {
			while ((response = br.readLine()) != null) {

				view.txtArea.appendText("> Eliza: " + response+"\n");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		sendToServer();
	}

}