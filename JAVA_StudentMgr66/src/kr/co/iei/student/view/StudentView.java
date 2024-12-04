package kr.co.iei.student.view;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.student.dao.StudentDao;
import kr.co.iei.student.vo.Student;

public class StudentView {
	Scanner sc;
	
	
	public StudentView() {
		super();
		sc = new Scanner(System.in);
	}


	public int mainMenu() {
		System.out.println("\n-------------------학생관리 프로그램v6----------------------\n");
		System.out.println("1.학생 정보 등록");
		System.out.println("2.전체 학생 출력");
		System.out.println("3.학생 1명 출력");
		System.out.println("4.학생 정보 수정");
		System.out.println("5.학생 정보 삭제");
		System.out.println("0.프로그램 종료");
		System.out.print("선택 >> ");
		int select = sc.nextInt();
		return select;
	}
	
	public Student insertStudent() {
		System.out.println("\n------------------------학생 정보 입력-----------------------------\n");
		System.out.print("학생 이름 입력: ");
		String name = sc.next();
		System.out.print("학생 나이 입력: ");
		int age = sc.nextInt();
		System.out.print("학생 주소 입력: ");
		sc.nextLine();
		String addr = sc.next();
		Student s = new Student(name, age, addr);
		return s;
	}
	
	public void insertMsg() {
		System.out.println("학생 정보 등록 완료");
	}
	
	public void updateMsg() {
		System.out.println("학생 수정 완료");
	}
	public void deleteMsg() {
		System.out.println("학생 삭제 완료");
	}
	public void wrongMsg() {
		System.out.println("일치하는 학생이 없습니다.");
	}
	
	public void printAllStudent(ArrayList<Student> students) {
		System.out.println("\n------------------------전체 학생 출력-----------------------------\n");
		System.out.println("이름\t나이\t주소");
		for(Student s : students) {
			System.out.println(s.getName() +"\t" + s.getAge() +"\t" + s.getAddr());
		}
	}
	
	public void printOneStudent(Student s) {
		System.out.println("\n------------------------학생 1명 출력-----------------------------\n");
		System.out.println("이름\t나이\t주소");
		System.out.println(s.getName() +"\t" + s.getAge() +"\t" + s.getAddr());
	}
	
	public String searchStudent() {
		System.out.print("학생의 이름을 입력하세요: ");
		String name = sc.next();
		return name;
	}
	
	

}
