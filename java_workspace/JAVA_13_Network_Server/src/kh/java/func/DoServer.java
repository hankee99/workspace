package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class DoServer {
	public void sever() {
		Scanner sc = new Scanner(System.in);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("www.naver.com","125.209.222.142");
		map.put("www.google.com","172.217.175.4");
		map.put("www.iei.co.kr","99.83.248.72");
		int port = 8888;
		
		ServerSocket server = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("[DNS서버 준비 완료]");
			
			
			
			
			while(true) {
				System.out.println("클라이언트 요청 대기...");
				Socket socket = server.accept();
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				dis = new DataInputStream(in);
				dos = new DataOutputStream(out);
				
				
				
				String dom = dis.readUTF();
				if(map.get(dom) == null) {
					dos.writeUTF("주소가 없습니다.");
				}else {
					dos.writeUTF(map.get(dom));
				}
				
				System.out.print("서버를 종료하시겠습니까?(y,n): ");
				String yn = sc.nextLine();
				if(yn.equals("y")) {
					dis.close();
					dos.close();
					break;
				}else if(yn.equals("n")) {
					dis.close();
					dos.close();
					continue;
				}
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
