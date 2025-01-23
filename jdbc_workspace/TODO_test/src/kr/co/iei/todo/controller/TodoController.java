package kr.co.iei.todo.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.todo.model.service.TodoService;
import kr.co.iei.todo.model.vo.Todo;

public class TodoController {
	Scanner sc;
	TodoService todoService;

	public TodoController() {
		super();
		sc = new Scanner(System.in);
		todoService = new TodoService();
	}

	public void main() {
		while (true) {
			System.out.println("\n---------- TODO LIST ----------\n");
			System.out.println("1. 전체 할일 목록 보기");
			System.out.println("2. 완료 목록 보기");
			System.out.println("3. 미완료 목록 보기");
			System.out.println("4. 할일 등록 하기");
			System.out.println("5. 할일 완료 처리하기");
			System.out.println("6. 할일 삭제 하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				selectAllTodo();
				break;
			case 2:
				selectComp();
				break;
			case 3:
				selectYet();
				break;
			case 4:
				insertTodo();
				break;
			case 5:
				updateComp();
				break;
			case 6:
				deleteTodo();
				break;
			case 0:
				System.out.println("프로그램 종료...");
				return;
			default:
				System.out.println("0~6사이의 숫자를 입력하시오.");
			}
		}
	}

	public void selectAllTodo() {
		System.out.println("\n---------- 전체 할일 보기 ----------\n");
		ArrayList<Todo> lst = todoService.selectAllTodo();
		if(!lst.isEmpty()) {
			lst.stream().map(i -> i.toString()).forEach(System.out::println);
		}else {
			System.out.println("목록 없음");
		}

	}

	public void selectComp() {
		System.out.println("\n---------- 완료 목록 보기 ----------\n");
		ArrayList<Todo> lst = todoService.selectComp();
		if(!lst.isEmpty()) {
			lst.stream().map(i -> i.toString()).forEach(System.out::println);
		}else {
			System.out.println("완료된 항목 없음");
		}

	}

	public void selectYet() {
		System.out.println("\n---------- 미완료 목록 보기 ----------\n");
		ArrayList<Todo> lst = todoService.selectYet();
		if(!lst.isEmpty()) {
			lst.stream().map(i -> i.toString()).forEach(System.out::println);
		}else {
			System.out.println("미완료 항목 없음");
		}

	}

	public void insertTodo() {
		System.out.println("\n---------- 할일 등록 하기 ----------\n");
		System.out.print("작성자 이름: ");
		String todoName = sc.next();
		sc.nextLine();
		System.out.print("할일 내용: ");
		String todoContent = sc.nextLine();
		Todo td = new Todo();
		td.setTodoName(todoName);
		td.setTodoContent(todoContent);
		int result = todoService.insertTodo(td);
		if(result>0) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");
		}

	}

	public void updateComp() {
		System.out.println("\n---------- 할일 완료처리 하기 ----------\n");
		System.out.print("완료처리할 일의 번호 입력: ");
		int todoList = sc.nextInt();
		int result = todoService.updateComp(todoList);
		if(result > 0) {
			System.out.println("변경 성공");
		}else {
			System.out.println("변경 실패");
		}

	}

	public void deleteTodo() {
		System.out.println("\n---------- 할일 삭제 하기 ----------\n");
		System.out.println("삭제할 할일 번호 입력: ");
		int todoList = sc.nextInt();
		int result = todoService.deleteTodo(todoList);
		if(result > 0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
	}

}
