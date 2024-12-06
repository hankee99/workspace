package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class BaseballServer {
	public void server() {
		Random r = new Random();
		
		int port  = 7289;
		
		
		ServerSocket server = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		
		
		try {
			server = new ServerSocket(port);
			System.out.println("[서버 준비 완료]");
			
			
			
			
			
			
			
			
			
			
			while(true) {
				ArrayList<Integer> lst = new ArrayList<>();
				for(int i=0; i<3; i++) {
					int num = r.nextInt(10);
					if(!lst.contains(num)) {
						lst.add(num);
					}else {
						i--;
					}
				}
				
				String ans = String.valueOf(lst.get(0)) + " " + String.valueOf(lst.get(1)) + " " + String.valueOf(lst.get(2));
				int cnt =1;
				System.out.println("클라이언트 접속 기다리는 중...");
				Socket socket = server.accept(); //상대 접속 기다리는중
				System.out.println("클라이언트가 접속 했습니다");
				System.out.println("서버숫자: " + ans);
				System.out.println("<<<<Game Start>>>>");
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				dis = new DataInputStream(in);
				dos = new DataOutputStream(out);
				
				
				
				while(true) {
					int stk = 0;
					int ball = 0;
					
					if(cnt > 9) {
						break;
					}
					
					
					//받기
					String clientMsg = dis.readUTF();
					if(clientMsg.equals(ans)) {
						dos.writeUTF("정답");
						break;
					}
					
					System.out.println("클라이언트가 입력한 수 -> " + clientMsg);
					
					//스트라이크, 볼 판정하는 코드
					if(clientMsg.charAt(0) != ans.charAt(0)) {
						if(ans.contains(String.valueOf(clientMsg.charAt(0)))) {
							ball++;
						}
					}else {
						stk++;
					}
					
					if(clientMsg.charAt(2) != ans.charAt(2)) {
						if(ans.contains(String.valueOf(clientMsg.charAt(2)))) {
							ball++;
						}
					}else {
						stk++;
					}
					
					if(clientMsg.charAt(4) != ans.charAt(4)) {
						if(ans.contains(String.valueOf(clientMsg.charAt(4)))) {
							ball++;
						}
					}else {
						stk++;
					}//판정 끝
					
					
					//보내기
					String msg = cnt + "회 ------> " +stk + "스트라이크"+ball +"볼";
					dos.writeUTF(msg);
					
					cnt++;
					
				}
				
				dis.close();
				dos.close();
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
