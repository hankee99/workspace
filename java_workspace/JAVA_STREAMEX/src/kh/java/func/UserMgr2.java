package kh.java.func;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMgr2 {
	ArrayList<User> members;
	Scanner sc;

	public UserMgr2() {
		super();
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		importUser();
	}

	public void main() {
		while (true) {
			System.out.println("1. 회원 정보 등록");
			System.out.println("2. 회원 정보 출력");
			System.out.println("3. 회원 정보 내보내기");
			System.out.println("4. 회원 정보 불러오기");
			System.out.print("선택 >> ");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				insertUser();
				break;
			case 2:
				printUser();
				break;
			case 3:
				exportUser();
				break;
			case 4:
				importUser();
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
		members.add(u);
		System.out.println("등록 완료");
		exportUser();
		

	}

	public void printUser() {
		if(members.size() == 0) {
			System.out.println("회원 정보가 없습니다.");
		}else {
			
			for(int i=0; i<members.size(); i++) {
				System.out.println("아이디: "+members.get(i).getId() + " 비밀번호: "+members.get(i).getPw()+" 이름: "+members.get(i).getName()+" 나이: "+members.get(i).getAge());
				
			}
			
		}

	}

	public void exportUser() {
		if(members.size() == 0) {
			System.out.println("내보낼 정보가 없습니다.");
		}else {
			
			ObjectOutputStream oos = null;
			try {
				FileOutputStream fos = new FileOutputStream("backup2.txt");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(members);
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
			System.out.println("내보내기 완료.");
			
		}

	}

	public void importUser() {
		File filecheck = new File("backup2.txt");
		if(!filecheck.exists()) {
			System.out.println("불러올 정보가 없습니다.");
			return;
		}
		
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream("backup2.txt");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			members = (ArrayList<User>)obj;
			System.out.println("불러오기 완료.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
