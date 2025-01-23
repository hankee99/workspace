package kr.co.iei.todo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.todo.model.dao.TodoDao;
import kr.co.iei.todo.model.vo.Todo;

public class TodoService {
	TodoDao todoDao;

	public TodoService() {
		super();
		todoDao = new TodoDao();
	}

	public ArrayList<Todo> selectAllTodo() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Todo> lst = todoDao.selectAllTodo(conn);
		JDBCTemplate.close(conn);
		return lst;
	}

	public ArrayList<Todo> selectComp() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Todo> lst = todoDao.selectComp(conn);
		JDBCTemplate.close(conn);
		return lst;
	}

	public ArrayList<Todo> selectYet() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Todo> lst = todoDao.selectYet(conn);
		JDBCTemplate.close(conn);
		return lst;
	}

	public int insertTodo(Todo td) {
		Connection conn = JDBCTemplate.getConnection();
		int result = todoDao.insertTodo(conn,td);
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComp(int todoList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = todoDao.updateComp(conn,todoList);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteTodo(int todoList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = todoDao.deleteTodo(conn,todoList);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	

}
