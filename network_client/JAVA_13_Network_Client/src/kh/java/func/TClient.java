package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TClient {
	public void client() {
		Scanner sc = new Scanner(System.in);
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		String serverIp = "1.220.236.74";
		int serverPort = 18080;
		
		try {
			socket = new Socket(serverIp,serverPort);
			System.out.println("서버 접속 완료");
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dos = new DataOutputStream(out);
			dis = new DataInputStream(in);
			
			while(true) {
				
				
				String msg = dis.readUTF();
				if(msg.equals("실망이에요ㅠㅠㅠㅠㅠㅠㅠㅠ")) {
					System.out.println("땡");
					break;
				}
				if(msg.equals("틀리셨습니다.. Bye~ Bye~")) {
					System.out.println("땡");
					break;
				}
				if(msg.equals("틀리셨습니다.. Bye~ Bye")) {
					System.out.println("땡");
					break;
				}
				System.out.println(msg);
				if(msg.charAt(msg.length()-1) == '?') {
					String str = sc.nextLine();
					dos.writeUTF(str);
					
					continue;
				}else if(msg.charAt(msg.length()-1) == '끝') {
					break;
				}else {
					continue;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
