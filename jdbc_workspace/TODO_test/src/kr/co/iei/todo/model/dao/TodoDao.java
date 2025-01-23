package kr.co.iei.todo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.todo.model.vo.Todo;

public class TodoDao {
	public ArrayList<Todo> selectAllTodo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from todo_tbl order by 1";
		ArrayList<Todo> lst = new ArrayList<Todo>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo td = new Todo(rset.getInt("todo_list"),rset.getString("todo_yn"),rset.getDate("todo_date"),rset.getString("todo_name"),rset.getString("todo_content"));
				lst.add(td);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lst;
	}

	public ArrayList<Todo> selectComp(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from todo_tbl where todo_yn = 'Y'  order by 1";
		ArrayList<Todo> lst = new ArrayList<Todo>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo td = new Todo(rset.getInt("todo_list"),rset.getString("todo_yn"),rset.getDate("todo_date"),rset.getString("todo_name"),rset.getString("todo_content"));
				lst.add(td);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lst;
	}

	public ArrayList<Todo> selectYet(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from todo_tbl where todo_yn = 'N'  order by 1";
		ArrayList<Todo> lst = new ArrayList<Todo>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo td = new Todo(rset.getInt("todo_list"),rset.getString("todo_yn"),rset.getDate("todo_date"),rset.getString("todo_name"),rset.getString("todo_content"));
				lst.add(td);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lst;
	}

	public int insertTodo(Connection conn, Todo td) {
		PreparedStatement pstmt = null;
		String query = "insert into todo_tbl values(todo_seq.nextval,'N',sysdate,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, td.getTodoName());
			pstmt.setString(2, td.getTodoContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateComp(Connection conn, int todoList) {
		PreparedStatement pstmt = null;
		String query = "update todo_tbl set todo_yn = 'Y' where todo_list = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoList);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteTodo(Connection conn, int todoList) {
		PreparedStatement pstmt = null;
		String query = "delete from todo_tbl where todo_list = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoList);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
