package kr.co.iei.student.controller;

import kr.co.iei.student.dao.StudentDao;
import kr.co.iei.student.view.StudentView;

public class StudentController {
	StudentView view;
	StudentDao dao;

	public StudentController() {
		super();
		view = new StudentView();
		dao = new StudentDao();
	}

	public void main() {
		while (true) {
			int select = view.mainMenu();
			switch (select) {
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
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	public void insertStudent() {
		dao.insertStudent(view.insertStudent());
		view.insertMsg();
	}

	public void printAllStudent() {
		view.printAllStudent(dao.getStudentList());
	}

	public void printOneStudent() {
		String name = view.searchStudent();
		if(dao.searchStudent(name) != -1) {
			view.printOneStudent(dao.getStudent(dao.searchStudent(name)));
		}else {
			view.wrongMsg();
		}
		
	}

	public void updateStudent() {
		String name = view.searchStudent();
		if(dao.searchStudent(name) != -1) {
			dao.updateStudent(dao.searchStudent(name), view.insertStudent());
			view.updateMsg();
		}else {
			view.wrongMsg();
		}
	}

	public void deleteStudent() {
		String name = view.searchStudent();
		if(dao.searchStudent(name) != -1) {
			dao.deleteStudent(dao.searchStudent(name));
			view.deleteMsg();
		}else {
			view.wrongMsg();
		}
	}

}
