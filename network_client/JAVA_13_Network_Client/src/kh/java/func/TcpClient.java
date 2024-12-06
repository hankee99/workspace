package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
	public void client() {
		Scanner sc = new Scanner(System.in);
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		
		String serverIp = "192.168.10.3";
		int serverPort = 7777;
		
		try {
			socket = new Socket(serverIp,serverPort);
			System.out.println("서버 접속 완료");
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dos = new DataOutputStream(out);
			dis = new DataInputStream(in);
			
			while(true) {
				
				String serverMsg =  dis.readUTF();
				if(serverMsg.equals("exit")) break;
				System.out.println("Server: " + serverMsg);
				System.out.print("To.Server: ");
				String msg = sc.nextLine();
				dos.writeUTF(msg);
				if(msg.equals("exit")) break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
