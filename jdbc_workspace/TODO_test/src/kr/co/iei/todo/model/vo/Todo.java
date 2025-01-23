package kr.co.iei.todo.model.vo;

import java.sql.Date;

public class Todo {
	private int todoList;
	private String todoYn;
	private Date todoDate;
	private String todoName;
	private String todoContent;
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Todo(int todoList, String todoYn, Date todoDate, String todoName, String todoContent) {
		super();
		this.todoList = todoList;
		this.todoYn = todoYn;
		this.todoDate = todoDate;
		this.todoName = todoName;
		this.todoContent = todoContent;
	}
	public int getTodoList() {
		return todoList;
	}
	public void setTodoList(int todoList) {
		this.todoList = todoList;
	}
	public String getTodoYn() {
		return todoYn;
	}
	public void setTodoYn(String todoYn) {
		this.todoYn = todoYn;
	}
	public Date getTodoDate() {
		return todoDate;
	}
	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}
	public String getTodoName() {
		return todoName;
	}
	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	@Override
	public String toString() {
		return todoList + "\t" + todoYn + "\t" + todoDate + "\t" + todoName
				+ "\t" + todoContent;
	}
	
	

}
