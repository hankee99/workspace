package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DoClient {
	public void client() {
		Scanner sc = new Scanner(System.in);
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		String serverIp = "192.168.10.3";
		int serverPort = 8888;
		
		try {
			socket = new Socket(serverIp, serverPort);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dos = new DataOutputStream(out);
			dis = new DataInputStream(in);
			System.out.print("알고싶은 도메인 주소를 입력: ");
			String dom = sc.nextLine();
			
			dos.writeUTF(dom);
			String addr = dis.readUTF();
			if(addr.equals("주소가 없습니다.")) {
				System.out.println(addr);
			}else {
				System.out.println("["+dom+"]의 주소는"+addr+"입니다.");
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
