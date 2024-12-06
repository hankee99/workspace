package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {
	public void server() {
		Scanner sc = new Scanner(System.in);
		int port  = 7777;
		
		ServerSocket server = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		
		try {
			server = new ServerSocket(port);
			System.out.println("[서버 준비 완료]");
			System.out.println("클라이언트 접속 기다리는 중...");
			Socket socket = server.accept();
			System.out.println("클라이언트 접속 완료");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			
			while(true) {
				//보내기
				System.out.print("To.Client: ");
				String msg = sc.nextLine();
				dos.writeUTF(msg);
				if(msg.equals("exit")) break;
				
				//받기
				String clientMsg = dis.readUTF();
				if(clientMsg.equals("exit")) break;
				System.out.println("Client: " + clientMsg);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				server.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
