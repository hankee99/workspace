package kh.java.func;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharStreamTest {
	public void test1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("저장할 파일명 입력: ");
		String filename = sc.nextLine();
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			System.out.println("종료는 exit를 입력하세요.");
			while(true) {
				System.out.print("입력: ");
				String str = sc.nextLine() + "\r\n";
				if(str.equals("exit\r\n")) break;
				
				fw.write(str);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void subStream() {
		Scanner sc = new Scanner(System.in);
		System.out.println("저장할 파일명 입력: ");
		String filename = sc.nextLine();
		BufferedWriter bw = null;
		
		try {
			FileWriter fw = new FileWriter("C:\\Users\\user1\\Desktop\\"+filename);
			bw = new BufferedWriter(fw);
			System.out.println("종료하려면 exit를 입력하세요.");
			while(true) {
				System.out.print("입력: ");
				String str = sc.nextLine();
				if(str.equals("exit")) break;
				bw.write(str);
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void readerStream() {
		Scanner sc = new Scanner(System.in);
		System.out.println("읽어올 파일명 입력: ");
		String filename = sc.nextLine();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader("C:\\Users\\user1\\Desktop\\"+filename);
			br = new BufferedReader(fr);
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
