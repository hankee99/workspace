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
		
		students[index++] = new Student("학생1",21,"경기");
		students[index++] = new Student("학생2",23,"서울");
		students[index++] = new Student("학생3",22,"인천");
		students[index++] = new Student("학생4",20,"부산");
		students[index++] = new Student("학생5",18,"안산");
		
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
				printAllStudent();
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
	
	public void printAllStudent() { //2.전체 학생 정보 출력
		System.out.println("-----------전체 학생 정보----------");
		System.out.println("이름\t나이\t주소");
		System.out.println("-----------------------------");
		for(int i=0; i<index; i++) {
			
			System.out.println(students[i].getName() + "\t" + students[i].getAge() + "\t" + students[i].getAddr());
			
		}
		
		
	}
	
	public void printOneStudent() { //3.이름을 입력받은 학생 정보 출력
		System.out.println("-----------학생 정보 열람----------");
		System.out.print("조회할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		if(searchStudent(name)==-1) {
			System.out.println("일치하는 이름이 없습니다.");
		}else {
			System.out.println("이름\t나이\t주소");
			System.out.println("-----------------------------");
			System.out.println(students[searchStudent(name)].getName() + "\t" + students[searchStudent(name)].getAge() + "\t" + students[searchStudent(name)].getAddr());
		}
		
		
		
	}
	
	public void modifyStudent() { //4.학생정보 수정
		System.out.println("-----------학생 정보 수정----------");
		System.out.print("수정할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		if(searchStudent(name)==-1) {
			System.out.println("일치하는 이름이 없습니다.");
		}else {
			System.out.print("수정할 이름을 입력하새요: ");
			String name2 = sc.next();
			System.out.print("수정할 나이를 입력하새요: ");
			int age = sc.nextInt();
			sc.nextLine();
			System.out.print("수정할 주소를 입력하새요: ");
			String addr = sc.next();
			
			students[searchStudent(name)].setName(name2);
			students[searchStudent(name2)].setAge(age);
			students[searchStudent(name2)].setAddr(addr);
			System.out.println("수정이 완료되었습니다.");
		}
		
		
		
		
	}
	
	public void deleteStudent() { //5.학생 정보 삭제
		System.out.println("-----------학생 정보 삭제----------");
		System.out.print("삭제할 학생의 이름을 입력하세요: ");
		String name = sc.next();
		if(searchStudent(name)==-1) {
			System.out.println("일치하는 이름이 없습니다.");
		}else {
			for(int i=searchStudent(name); i<index-1; i++) {
				students[i] = students[i+1];
			}
			students[index-1] = null;
			index--;
			System.out.println("삭제가 완료되었습니다.");
		}
		
		
	}
	
	
	public int searchStudent(String name) { //해당 이름의 학생의 인덱스를 반환하는 메소드
		int a =-1;
		for(int i=0; i<index; i++) {
			if(name.equals(students[i].getName())) {
				a=i;
				break;
			}
		}
		return a;
	}
	
	
}
