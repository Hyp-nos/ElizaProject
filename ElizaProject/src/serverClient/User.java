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

public class User implements Runnable {
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

			//pw.write("I don't understand what you want to say\n");
			pw.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void dealWithMessageFromClient(String message) throws IOException {
		boolean foundResponse = false;

			String[] words = message.split("[ ,\\.!?]");

			for (String key : words) {
				if (key == "you" || key == "u") {
					pw.write("why do you keep talking about me?\n");
					pw.flush();
				}else pw.write("I dont understand that \n");
			}

		}
	}

