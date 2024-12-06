package kh.java.func;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {
	public void client() {
		int serverPort  = 8888;
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket();
			String msg = "난 UDP 클라이언트야";
			byte[] outMsg = msg.getBytes();
			
			InetAddress serverIp = InetAddress.getByName("127.0.0.1");
			DatagramPacket outPacket = new DatagramPacket(outMsg, outMsg.length,serverIp,serverPort);
			socket.send(outPacket);
			System.out.println("데이터 전송 완료");
			byte[] inMsg = new byte[1024];
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);
			socket.receive(inPacket);
			String serverMsg = new String(inMsg).trim();
			System.out.println(serverMsg);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			socket.close();
		}
	}

}
