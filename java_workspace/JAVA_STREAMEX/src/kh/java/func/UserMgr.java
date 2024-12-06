package kh.java.func;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UserMgr {
	User user;
	Scanner sc;
	public UserMgr() {
		super();
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		user = null;
	}
	public void main() {
		while(true) {
			System.out.println("1. 회원 정보 등록");
			System.out.println("2. 회원 정보 출력");
			System.out.println("3. 회원 정보 내보내기");
			System.out.println("4. 회원 정보 불러오기");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			
			switch(select) {
			case 1:
				insertUser();
				break;
			case 2:
				printUser();
				break;
			case 3:
				exportUser2();
				break;
			case 4:
				importUser2();
				break;
				
			}
			
		}
		
		
	}
	
	public void insertUser() {
		System.out.print("아이디 입력: ");
		String id = sc.next();
		System.out.print("비밀번호 입력: ");
		String pw = sc.next();
		System.out.print("이름 입력: ");
		String name = sc.next();
		System.out.print("나이 입력: ");
		int age = sc.nextInt();
		
		
		User u = new User(id, pw, name, age);
		user  = u;
		System.out.println("등록 완료");
		
		
	}
	
	public void printUser() {
		if(user == null) {
			System.out.println("회원 정보가 없습니다.");
		}else {
			System.out.println("아이디: "+user.getId());
			System.out.println("비밀번호: "+user.getPw());
			System.out.println("이름: "+user.getName());
			System.out.println("나이: "+user.getAge());
		}
		
	}
	
	public void exportUser() {
		if(user == null) {
			System.out.println("내보낼 정보가 없습니다.");
		}else {
			String exportData = user.getId()+"/" + user.getPw()+"/" + user.getName()+"/" + user.getAge();
			BufferedWriter bw = null;
			try {
				FileWriter fw = new FileWriter("backup.txt");
				bw = new BufferedWriter(fw);
				bw.write(exportData);
				System.out.println("내보내기 완료.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void importUser() {
		File checkFile = new File("backup.txt");
		if(!checkFile.exists()) {
			System.out.println("읽어올 파일이 없습니다.");
			return;
		}
		
		BufferedReader br = null;
		
		try {
			FileReader fr = new FileReader("backup.txt");
			br = new BufferedReader(fr);
			String str = br.readLine();
			String[] arr = str.split("/");
			
			String id = arr[0];
			String pw = arr[1];
			String name = arr[2];
			int age = Integer.parseInt(arr[3]);
			User u = new User(id, pw, name, age);
			user = u;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void exportUser2() {
		if(user == null) {
			System.out.println("내보낼 정보가 없습니다.");
		}else {
			ObjectOutputStream oos = null;
			try {
				FileOutputStream fos = new FileOutputStream("backup1.txt");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(user);
				System.out.println("내보내기 완료.");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void importUser2() {
		File checkFile = new File("backup1.txt");
		if(!checkFile.exists()) {
			System.out.println("읽어올 파일이 없습니다.");
			return;
		}
		
		ObjectInputStream ois = null;
		
		
		try {
			FileInputStream fis = new FileInputStream("backup1.txt");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			User u = (User)obj;
			user = u;
			System.out.println("가져오기 완료.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
