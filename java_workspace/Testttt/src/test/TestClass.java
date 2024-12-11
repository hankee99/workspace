package test;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.ServerSocket;

import java.net.Socket;

import java.text.SimpleDateFormat;

import java.util.Date;

public class TestClass {

	public void chattingServer() {

		int port = 7287;

		ServerSocket serverSocket = null;

		DataInputStream dis = null;

		DataOutputStream dos = null;

		try {

			serverSocket = new ServerSocket(port);

			Socket clientSocket = serverSocket.accept();
			System.out.println("클라 접속 완료");

			InputStream in = clientSocket.getInputStream();

			OutputStream out = clientSocket.getOutputStream();

			dis = new DataInputStream(in);

			dos = new DataOutputStream(out);

			dos.writeUTF("[서버 연결 성공]");

			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

			String now = sdf.format(date);

			dos.writeUTF("[현재시간] : " + now);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				serverSocket.close();

				dis.close();

				dos.close();

				

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}