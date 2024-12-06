package kh.java.func;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
	public void client() {
		Scanner sc = new Scanner(System.in);
		String serverIp = "127.0.0.1";
		int serverPort = 9999;
		
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		BufferedOutputStream bos = null;
		
		try {
			socket = new Socket(serverIp, serverPort);
			System.out.println("파일 서버 접속 완료");
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			
			int fileCount = dis.readInt();
			System.out.println("파일 수: " + fileCount);
			for(int i=0; i<fileCount; i++) {
				System.out.println(dis.readUTF());
			}
			
			System.out.print("받아올 파일명: ");
			String fileName = sc.nextLine();
			dos.writeUTF(fileName);
			
			int fileSize = dis.readInt();
			byte[] fileContent = new byte[fileSize];
			
			dis.read(fileContent);
			
			FileOutputStream fos = new FileOutputStream(fileName);
			bos = new BufferedOutputStream(fos);
			bos.write(fileContent);
			bos.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dis.close();
				dos.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
