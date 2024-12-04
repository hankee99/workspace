package kr.co.iei.point.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import kr.co.iei.point.vo.Grade;

public class PointDao {
	ArrayList<Grade> members;

	public PointDao() {
		super();
		importMember();
	}
	
	public void insertMember(Grade g) {
		members.add(g);
		exportMember();
	}
	
	public ArrayList<Grade> getMemberList(){
		return members;
	}
	
	public Grade getMember(int idx) {
		return members.get(idx);
	}
	
	public int searchMember(String name) {
		for(int i=0; i<members.size(); i++) {
			if(name.equals(members.get(i).getName())) return i;
		}
		return -1;
	}
	
	public void updateMember(int idx, Grade g) {
		members.set(idx, g);
		exportMember();
	}
	
	public void deleteMember(int idx) {
		members.remove(idx);
		exportMember();
	}
	
	public void exportMember() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("backup.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(members);
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
	
	public void importMember() {
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream("backup.txt");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			members = (ArrayList<Grade>)obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			members = new ArrayList<Grade>();
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
