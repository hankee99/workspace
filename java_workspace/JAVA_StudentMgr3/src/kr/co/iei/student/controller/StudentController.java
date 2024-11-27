package kr.co.iei.student.controller;
import java.util.ArrayList;
import java.util.Scanner;
import kr.co.iei.student.vo.Student;

public class StudentController {
	Scanner sc;
	ArrayList<Student> stuArr = new ArrayList<Student>();

	public StudentController() {
		super();
		sc = new Scanner(System.in);
		
		stuArr.add(new Student("학생1",1,"서울 양천"));
		stuArr.add(new Student("학생2",2,"서울 강서"));
		stuArr.add(new Student("학생3",3,"서울 노원"));
		stuArr.add(new Student("학생4",4,"서울 중랑"));
		stuArr.add(new Student("학생5",5,"서울 마포"));
	}
	
	//메인메뉴를 진행하는 메소드
		public void main() {
			while(true) {
				System.out.println("\n-----------학생관리프로그램v3------------\n");
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
					updateStudent();
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
		
		public void insertStudent() {
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
			
			stuArr.add(s);
		}
		
		public void printAllStudent() {
			System.out.println("-----------전체 학생 정보----------");
			System.out.println("이름\t나이\t주소");
			System.out.println("-----------------------------");
			
			for(Student i : stuArr) {
				System.out.println(i.getName() +"\t" + i.getAge() +"\t" + i.getAddr());
			}
			
		}
		
		public void printOneStudent() {
			System.out.println("-----------학생 정보 열람----------");
			System.out.print("조회할 학생의 이름을 입력하세요: ");
			String name = sc.next();
			
			int i = searchStudent(name);
			if(i == -1) {
				System.out.println("일치하는 이름이 없습니다.");
			}else {
				System.out.println("이름\t나이\t주소");
				System.out.println("-----------------------------");
				System.out.println(stuArr.get(i).getName() + "\t" + stuArr.get(i).getAge() + "\t" + stuArr.get(i).getAddr());
			}
		}
		
		public void updateStudent() {
			System.out.println("-----------학생 정보 수정----------");
			System.out.print("수정할 학생의 이름을 입력하세요: ");
			String name = sc.next();
			int i =searchStudent(name);
			if(i==-1) {
				System.out.println("일치하는 이름이 없습니다.");
			}else {
				System.out.print("수정할 이름을 입력하새요: ");
				String name2 = sc.next();
				System.out.print("수정할 나이를 입력하새요: ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.print("수정할 주소를 입력하새요: ");
				String addr = sc.next();
				
				stuArr.set(i, new Student(name2,age,addr));
			}
		}
		
		public void deleteStudent() {
			System.out.println("-----------학생 정보 삭제----------");
			System.out.print("삭제할 학생의 이름을 입력하세요: ");
			String name = sc.next();
			
			int i = searchStudent(name);
			stuArr.remove(i);
			
		}
		
		public int searchStudent(String name) {
			for(int i=0; i<stuArr.size(); i++) {
				if(stuArr.get(i).getName().equals(name)) {
					return i;
				}
			}
			
			return -1;
		}

}
