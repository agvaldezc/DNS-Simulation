import java.io.*;
import java.net.*;

public class Cliente {
	
	public static void main (String args[]) {

		Socket tcpSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {

			BufferedReader stdln = new BufferedReader(new InputStreamReader(System.in));

			String userInput;
			String serverReply;

			while ((userInput = stdln.readLine()) != null) {
				
				InetAddress server = InetAddress.getByName("127.0.0.1");

				DatagramSocket socket = new DatagramSocket();

				byte[] dataOut = new byte[userInput.length()];

				dataOut = userInput.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(dataOut, dataOut.length, server, 35123);

				socket.send(sendPacket);

				byte[] portIn, addressIn;

				portIn = new byte[65536];
				addressIn = new byte[65536];

				DatagramPacket portPacket = new DatagramPacket(portIn, 65536);
				DatagramPacket addressPacket = new DatagramPacket(addressIn, 65536);

				socket.receive(portPacket);
				socket.receive(addressPacket);

				String port = new String(portPacket.getData(), 0, portPacket.getLength());
				String address = new String(addressPacket.getData(), 0, addressPacket.getLength());

				System.out.println(port);
				System.out.println(address);

				if (port.equals("ERROR") || address.equals("ERROR")) {
					System.out.println("Could not resolve name");
				} else {

					int parsedPort = Integer.parseInt(port);
					tcpSocket = new Socket(address, parsedPort);

					out = new PrintWriter(tcpSocket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));

					out.println("request");

					System.out.println(in.readLine());
				}
				
			}
		} 
		catch (UnknownHostException ex) {System.err.println(ex);}
		catch (SocketException se) {System.err.println(se);}
		catch (IOException e) {System.err.println(e);} 
	}
}