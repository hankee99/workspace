package kh.java.func;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class FileServer {
	public void server() {
		int port = 9999;
		ServerSocket server = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		BufferedInputStream bis = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("파일 서버 준비완료");
			System.out.println("클라이언트 접속 대기.......");
			Socket socket = server.accept();
			System.out.println("클라이언트 접속 완료");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			
			File dir = new File("files");
			File[] files = dir.listFiles();
			dos.writeInt(files.length);
			for(File file : files) {
				dos.writeUTF(file.getName());
			}
			
			String fileName = dis.readUTF();
			
			File sendFile = new File("files/"+fileName);
			int fileSize = (int)sendFile.length();
			System.out.println("파일크기:" + fileSize);
			byte[] fileContent = new byte[fileSize];
			
			FileInputStream fis = new FileInputStream("files/"+fileName);
			bis = new BufferedInputStream(fis);
			bis.read(fileContent); //배열에 파일데이터를 저장
			dos.writeInt(fileSize); //파일 크기 전송
			
			dos.write(fileContent);//파일데이터 전송
			dos.flush();//버퍼에 남아있을 수 있는 데이터 강제로 보내기
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				server.close();
				dis.close();
				dos.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
