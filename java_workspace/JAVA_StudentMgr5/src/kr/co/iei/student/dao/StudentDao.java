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
		students = new ArrayList<Student>();
		importStudent();
	}
	
	public void insertStudent(Student s) {
		students.add(s);
		exportStudent();
	}
	
	public ArrayList<Student> getStudentList() {
		
		return students;
	}
	
	public Student getStudent(String name) {
		Student a = null;
		for(Student s : students) {
			if(name.equals(s.getName())) {
				a = s;
				break;
			}
		}
		return a;
	}
	
	public void updateStudent(String name, int age, String addr) {
		students.get(searchStudent(name)).setAge(age);
		students.get(searchStudent(name)).setAddr(addr);;
		exportStudent();
	}
	
	public void deleteStudent(int searchIndex) {
		students.remove(searchIndex);
		exportStudent();
	}
	
	
	
	
	public int searchStudent(String name) {
		for(int i=0; i<students.size(); i++) {
			Student s = students.get(i);
			if(s.getName().equals(name)) {
				return i;
			}
		}
		return -1;
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
			
			Object obj = (Object)ois.readObject();
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
