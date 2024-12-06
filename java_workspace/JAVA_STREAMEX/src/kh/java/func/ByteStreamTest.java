package kh.java.func;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ByteStreamTest {
	Scanner sc;
	
	
	public ByteStreamTest() {
		super();
		sc = new Scanner(System.in);
	}


	public void test1() {
		System.out.print("파일명을 입력하세요: ");
		String filename = sc.next();
		sc.nextLine();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\Users\\user1\\Desktop\\" + filename);
			System.out.println("종료는 exit를 입력하세요.");
			while(true) {
				System.out.print("내용 입력: ");
				String str = sc.next() + "\r\n";
				
				if(str.equals("exit\r\n")) break;
				
				byte[] data = str.getBytes();
				fos.write(data);
			}
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
