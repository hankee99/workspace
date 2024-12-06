package kh.java.func;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {
	public void server() {
		int port  = 8888;
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket(port);
			System.out.println("서버 소켓 생성 완료");
			
			byte[] inMsg = new byte[1024];
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			socket.receive(inPacket); //클라이언트의 메시지를 기다리는 코드
			
			String clientMsg = new String(inMsg).trim();
			System.out.println(clientMsg);
			
			InetAddress clientIp = inPacket.getAddress();
			int clientPort = inPacket.getPort();
			String msg = "난 UDP Server다!!";
			byte[] outMsg = msg.getBytes();
			
			DatagramPacket outPacket = new DatagramPacket(outMsg, outMsg.length, clientIp, clientPort);
			socket.send(outPacket);
		} catch (SocketException e) {
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
