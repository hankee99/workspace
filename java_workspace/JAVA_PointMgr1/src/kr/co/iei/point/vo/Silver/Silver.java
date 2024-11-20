package kr.co.iei.point.vo.Silver;

public class Silver {
	
	private String rank;
	private String name;
	private int point;
	
	public Silver() {
		
	}
	
	public Silver(String rank, String name, int point) {
		this.rank = rank;
		this.name = name;
		this.point = point;
	}
	//getter
	public String getRank() {
		return rank;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoint() {
		return point;
	}
	//setter
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
