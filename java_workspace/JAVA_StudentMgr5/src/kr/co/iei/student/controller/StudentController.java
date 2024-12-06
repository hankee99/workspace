package kr.co.iei.student.controller;

import java.util.Scanner;

import kr.co.iei.student.dao.StudentDao;
import kr.co.iei.student.vo.Student;

public class StudentController {
	Scanner sc;
	StudentDao dao;
	
	public StudentController() {
		super();
		sc = new Scanner(System.in);
		dao = new StudentDao();
	}
	
	public void main() {
		while(true) {
			
			System.out.println("\n----------------학생관리 프로그램v5-----------------");
			System.out.println("1. 학생 정보 등록");
			System.out.println("2. 전체 학생 출력");
			System.out.println("3. 학생 1명 정보 출력");
			System.out.println("4. 학생 정보 수정");
			System.out.println("5. 학생 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
			case 1:
				insertStudent();
				break;
			case 2:
				printAllStudent();
				break;
			case 3:
				printOneStudent();
				break;
			case 4:
				updateStudent();
				break;
			case 5:
				deleteStudent();
				break;
			case 0:
				return;
			}
		}
	}
	
	public void insertStudent() {
		System.out.println("\n-------------------학생정보 등록----------------\n");
		System.out.print("등록할 학생 이름 입력: ");
		String name = sc.next();
		System.out.print("등록할 학생 나이 입력: ");
		int age = sc.nextInt();
		System.out.print("등록할 학생 주소 입력: ");
		sc.nextLine();
		String addr = sc.next();
		Student s = new Student(name, age, addr);
		dao.insertStudent(s);
		System.out.println("학생 정보 등록 완료.");
	}
	
	public void printAllStudent() {
		System.out.println("\n--------------전체 학생 출력--------------\n");
		System.out.println("이름\t나이\t주소");
		System.out.println("--------------------------------------");
		for(Student s : dao.getStudentList()) {
			System.out.println(s.getName() + "\t" + s.getAge() + "\t" + s.getAddr());
		}
		
		
	}
	
	public void printOneStudent() {
		System.out.println("\n--------------학생 1명 정보 출력--------------\n");
		System.out.print("이름: ");
		String name = sc.next();
		
		Student stu = dao.getStudent(name);
		if(stu == null) {
			System.out.println("일치하는 학생이 없습니다.");
		}else {
			System.out.println("이름\t나이\t주소");
			System.out.println("--------------------------------------");
			System.out.println(stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddr());
		}
	}
	
	public void updateStudent() {
		System.out.println("\n--------------학생 정보 수정--------------\n");
		System.out.print("이름: ");
		String name = sc.next();
		
		if(dao.searchStudent(name) != -1) {
			System.out.print("수정할 나이를 입력하세요: ");
			int age = sc.nextInt();
			System.out.print("수정할 주소를 입력하세요: ");
			sc.nextLine();
			String addr = sc.next();
			dao.updateStudent(name, age, addr);
			System.out.println("수정이 완료되었습니다.");
		}else {
			System.out.println("일치하는 학생이 없습니다.");
		}
		
	}
	
	public void deleteStudent() {
		System.out.println("\n--------------학생 정보 삭제--------------\n");
		System.out.print("이름: ");
		String name = sc.next();
		if(dao.searchStudent(name) != -1) {
			dao.deleteStudent(dao.searchStudent(name));
			System.out.println("삭제가 완료되었습니다.");
		}else {
			System.out.println("일치하는 학생이 없습니다.");
		}
	}
	
	

}
