package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClass {
	public void client() {
		Scanner sc = new Scanner(System.in);
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		String serverIp = "192.168.10.37";
		int serverPort = 7289;
		
		try {
			socket = new Socket(serverIp,serverPort);
			
			System.out.println("서버 접속 완료");
			System.out.println("<<<<Game Start>>>>");
			
			OutputStream out = socket.getOutputStream();
			 InputStream in = socket.getInputStream();
			 dos = new DataOutputStream(out);
			 dis = new DataInputStream(in);
			
			
			 while(true) {
				 
				 System.out.print("숫자 입력: ");
				 String num = sc.nextLine();
				 dos.writeUTF(num);
				 
				 String stb = dis.readUTF();
				 System.out.println(stb);
				 if(stb.charAt(0) == '9') {
					 System.out.println("끝");
					 break;
				 }else if(stb.equals("정답")) {
					 System.out.println("축하!");
					 break;
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
