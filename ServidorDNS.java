import java.net.*;
import java.io.*;

public class ServidorDNS {
   static DatagramSocket socket;

   public static void main(String args[]){

   		String responsePort;
   		String responseAddress;

	    try{
	        socket = new DatagramSocket(35123);
	    } catch (Exception e){ System.err.println ("Unable to bind port"); }

		while (true) {
			try {
				byte[] dataIn;

				dataIn = new byte[65536];

				DatagramPacket receivedPacket = new DatagramPacket(dataIn, 65536);

				socket.receive(receivedPacket);

				String name = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

				System.out.println(name);

				if (name.equals("lab")) {
					responseAddress = "127.0.0.1";
					responsePort = "5000";
				} else if (name.equals("sala1")) {
					responsePort = "6000";
					responseAddress = "127.0.0.1";
				} else if (name.equals("sala2")) {
					responsePort = "7000";
					responseAddress = "127.0.0.1";
				} else if (name.equals("biblioteca")) {
					responsePort = "8000";
					responseAddress = "127.0.0.1";
				} else {
					responsePort = "ERROR";
					responseAddress = "ERROR";
				}

				byte[] portOut = new byte[responsePort.length()];
				byte[] addressOut = new byte[responseAddress.length()];

				portOut = responsePort.getBytes();
				addressOut = responseAddress.getBytes();

				DatagramPacket portPacket = new DatagramPacket(portOut, portOut.length, receivedPacket.getAddress(), receivedPacket.getPort());
				DatagramPacket addressPacket = new DatagramPacket(addressOut, addressOut.length, receivedPacket.getAddress(), receivedPacket.getPort());

				System.out.println(responsePort);
				System.out.println(responseAddress);

				socket.send(portPacket);
				socket.send(addressPacket);


				
			}	catch (IOException ioe){ System.err.println ("Error : " + ioe); }
		}	
	}
}