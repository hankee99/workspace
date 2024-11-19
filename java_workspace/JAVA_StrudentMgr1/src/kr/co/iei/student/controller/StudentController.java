package kr.co.iei.student.controller;


import java.util.Scanner;

import javax.annotation.processing.SupportedSourceVersion;

import kr.co.iei.student.vo.Student;
public class StudentController {
	//학생 1명 정보를 Student라는 객체 타입으로 저장
	//학생 10명을 저장하려면 Student가 10개->같은 자료형 10개이므로 Student[]로 관리
	Student[] students;
	Scanner sc;
	int index;
	
	public StudentController() {
		students = new Student[10];
		sc = new Scanner(System.in);
		index =0;
	}
	
	//메인메뉴를 진행하는 메소드
	public void main() {
		while(true) {
			System.out.println("\n-----------학생관리프로그램v2------------\n");
			System.out.println("1. 학생 정보 등록");
			System.out.println("2. 학생 정보 출력(전체)");
			System.out.println("3. 학생 정보 출력(이름 일치 1명)");
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
				printStudent();
				break;
			case 3:
				printOneStudent();
				break;
			case 4:
				modifyStudent();
				break;
			case 5:
				deleteStudent();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
		
	} //main 종료
	
	public void insertStudent() { //1.학생 정보 입력
		System.out.println("\n----------학생 정보 등록----------\n");
		System.out.print("이름을 입력하새요: ");
		String name = sc.next();
		System.out.print("나이를 입력하새요: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("주소를 입력하새요: ");
		String addr = sc.next();
		//객체를 생성하는 방법의 수 = 해당 클래스의 생성자의 수
		Student s = new Student(name,age,addr);
		
		students[index] = s;
		index++;
		System.out.println("등록이 완료되었습니다.");
	}// insert종료
	
	public void printStudent() { //2.전체 학생 정보 출력
		
		for(int i=0; i<index; i++) {
			System.out.print(students[i].getName() + "\t");
			System.out.print(students[i].getAge() + "\t");
			System.out.print(students[i].getAddr() + "\n");
		}
		
		
	}
	
	public void printOneStudent() { //3.이름을 입력받은 학생 정보 출력
		System.out.print("조회할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		int a =0;
		for(int i=0; i<index; i++) {
			if(name.equals(students[i].getName())) {
				a=i;
			}
		}
		
		System.out.print(students[a].getName() + "\t");
		System.out.print(students[a].getAge() + "\t");
		System.out.print(students[a].getAddr() + "\n");
		
	}
	
	public void modifyStudent() { //4.학생정보 수정
		System.out.print("수정할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		int a =0;
		for(int i=0; i<index; i++) {
			if(name.equals(students[i].getName())) {
				a=i;
			}
		}
		
		System.out.print("이름을 입력하새요: ");
		String name2 = sc.next();
		System.out.print("나이를 입력하새요: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("주소를 입력하새요: ");
		String addr = sc.next();
		
		students[a].setName(name2);
		students[a].setAge(age);
		students[a].setAddr(addr);
		System.out.println("수정이 완료되었습니다.");
		
	}
	
	public void deleteStudent() { //5.학생 정보 삭제
		System.out.print("삭제할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		int a =0;
		for(int i=0; i<index; i++) {
			if(name.equals(students[i].getName())) {
				a=i;
			}
		}
		
		for(int i=a; i<index-1; i++) {
			students[i] = students[i+1];
		}
		index--;
		System.out.println("삭제가 완료되었습니다.");
	}
	
	
}
