import java.net.*;
import java.io.*;
import java.util.*;

public class Sala2 {

	public static void main(String[] args) throws IOException {

		String request;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(7000);
		} catch (IOException e) {
			System.err.println("Could not listen to port: 7000");
			System.exit(1);
		}

		Socket socket = null;

		try {
			socket = serverSocket.accept();


		} catch (IOException e) {
			System.err.println("Accept failed");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		while ((request = in.readLine()) != null) {
			out.println("Servidor Sala2: " + new Date());
		}

		out.close();
		in.close();
		socket.close();
		serverSocket.close();
	}

}