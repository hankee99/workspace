package kr.co.iei.point.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import kr.co.iei.point.vo.Grade;
import kr.co.iei.point.vo.Silver;
import kr.co.iei.point.vo.Gold;
import kr.co.iei.point.vo.Vip;
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
	
	public void updateMember(String grade, String name,int point) {
		if(grade.equals("silver")) {
			members.set(searchMember(name), new Silver(grade,name,point));
		}else if(grade.equals("gold")) {
			members.set(searchMember(name), new Gold(grade,name,point));
		}else if(grade.equals("vip")) {
			members.set(searchMember(name), new Vip(grade,name,point));
		}
		
		
		members.get(searchMember(name)).setGrade(grade);
		members.get(searchMember(name)).setPoint(point);
		exportMember();
	}
	
	public void deleteMember(String name) {
		members.remove(searchMember(name));
		exportMember();
	}
	
	public int searchMember(String name) {
		for(int i=0; i<members.size(); i++) {
			if(members.get(i).getName().equals(name)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public Grade getMember(String name) {
		Grade a = null;
		for(Grade s : members) {
			if(name.equals(s.getName())) {
				a = s;
				break;
			}
		}
		return a;
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
