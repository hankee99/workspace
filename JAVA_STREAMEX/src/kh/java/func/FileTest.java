package kh.java.func;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
	public void test1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("파일명 입력: ");
		String filename = sc.nextLine();
		
		File file = new File(filename);
		if(file.exists()) {
			System.out.println("파일 있음");
			System.out.println("파일 이름: "+ file.getName());
			System.out.println("파일 상대경로: "+file.getPath());
			System.out.println("파일 절대경로: "+file.getAbsolutePath());
			System.out.println("파일 크기: "+file.length()+"Byte");
			System.out.print("해당 파일을 삭제하겠습니까?(1.yes)");
			int select = sc.nextInt();
			if(select == 1) {
				boolean flag = file.delete();
				System.out.println(flag);
			}
		}else {
			System.out.println("파일 없음");
		}
	}
	
	public void test2() {
		Scanner sc = new Scanner(System.in);
		
		String filename = null;
		
		
		while(true) {
			System.out.print("저장할 파일명 입력: ");
			filename = sc.nextLine();
			File file = new File("test/"+filename);
			if(file.exists()) {
				System.out.println("이미 존재하는 파일입니다.");
			}else {
				break;
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("test/"+filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
