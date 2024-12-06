package kr.co.iei.student.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import kr.co.iei.student.vo.Student;
public class StudentDao {
	ArrayList<Student> students;

	public StudentDao() {
		super();
		importStudent();
	}
	
	public void insertStudent(Student s) {
		students.add(s);
		exportStudent();
	}
	
	public ArrayList<Student> getStudentList() {
		return students;
	}
	
	public Student getStudent(int idx) {
		return students.get(idx);
	}
	
	public int searchStudent(String name) {
		for(int i=0; i<students.size(); i++) {
			if(name.equals(students.get(i).getName())) return i;
		}
		return -1;
	}
	
	public void updateStudent(int idx, Student s) {
		students.set(idx, s);
		exportStudent();
	}
	
	public void deleteStudent(int idx) {
		students.remove(idx);
		exportStudent();
	}
	
	public void exportStudent() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("backup.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(students);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void importStudent() {
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream("backup.txt");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			students = (ArrayList<Student>)obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			students = new ArrayList<Student>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ois != null) ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
