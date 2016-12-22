package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.Socket;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;



public class ElizaModel implements Runnable {
	ElizaView view;
	Socket s;
	BufferedWriter pw;
	BufferedReader br;
	String response;

	public ElizaModel(ElizaView view) {
		this.view = view;
		// Background music
		try {
			final Task task = new Task() {

		        @Override
		        protected Object call() throws Exception {
		            AudioClip audio = new AudioClip(getClass().getResource("aa.mp3").toExternalForm());
		            audio.setVolume(0.5f);
		            audio.setCycleCount(20);
		            audio.play();
		            return null;
		        }
		    };
		    Thread thread = new Thread(task);
		    thread.start();
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
			
			view.txtArea.appendText("> you: "+ " :" + userinputString + "\n");
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
				view.txtInput.clear();
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