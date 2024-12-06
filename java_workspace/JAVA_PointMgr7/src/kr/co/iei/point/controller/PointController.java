package kr.co.iei.point.controller;

import kr.co.iei.point.dao.PointDao;
import kr.co.iei.point.view.PointView;

public class PointController {
	PointView view;
	PointDao dao;

	public PointController() {
		super();
		view = new PointView();
		dao = new PointDao();
	}

	public void main() {
		while (true) {
			int select = view.mainMenu();
			switch (select) {
			case 1:
				insertMember();
				break;
			case 2:
				printAllMember();
				break;
			case 3:
				printOneMember();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				deleteMember();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	public void insertMember() {
		dao.insertMember(view.insertMember());
		view.insertMsg();
	}

	public void printAllMember() {
		view.printAllMember(dao.getMemberList());
	}

	public void printOneMember() {
		String name = view.searchMember();
		
		if(dao.searchMember(name) != -1) {
			view.printOneMember(dao.getMember(dao.searchMember(name)));
		}else {
			view.wrongMsg();
		}
	}

	public void updateMember() {
		String name = view.searchMember();
		if(dao.searchMember(name) != -1) {
			dao.updateMember(dao.searchMember(name), view.insertMember());
			view.updateMsg();
		}else {
			view.wrongMsg();
		}

	}

	public void deleteMember() {
		String name = view.searchMember();
		if(dao.searchMember(name) != -1) {
			dao.deleteMember(dao.searchMember(name));
			view.deleteMsg();
		}else {
			view.wrongMsg();
		}
	}

}
