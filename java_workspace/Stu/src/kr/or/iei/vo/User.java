package kr.or.iei.vo;

public class User {
	

	private String id;

	private String pw;

	private String name;

	private int age;

	private String phone;

	public User() {

		super();

	}
	
	

	public User(String id, String pw, String name, int age, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}



	public String getId() {

		return id;

	}

	public void setId(String id) {

		this.id = id;

	}

	public String getPw() {

		return pw;

	}

	public void setPw(String pw) {

		this.pw = pw;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getAge() {

		return age;

	}

	public void setAge(int age) {

		this.age = age;

	}

	public String getPhone() {

		return phone;

	}

	public void setPhone(String phone) {

		this.phone = phone;

	}

}
