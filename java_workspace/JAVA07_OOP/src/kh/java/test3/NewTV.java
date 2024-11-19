package kh.java.test3;

public class NewTV {
	
	private int inch;
	private boolean power;
	private int channel;
	private int volume;
	
	//생성자: 객체를 생성할때만 호출하는 메소드
	//만드는법
	//1.접근제어 지시자 public
	//2.리턴타입 없음(void가 아니라 아예 없음)
	//3.메소드이름: 클래스명과 동일
	//4.매개변수는 생성자의 역할에 따라 다름
	
	//만약 클래스 생성할 때 생성자를 1개도 작성하지 않으면 JVM이 실행할 때 기본생성자를 자동으로 작성
	//단, 생성자를 한개라도 작성하면 JVM은 기본생성자를 작성하지 않음
	//생성자는 필요에 따라서 오버로딩을 통해서 여러 개 만드는 것이 가능
	
	//기본생성자 : 생성자에 매개변수가 없고, 값을 초기화하지않고 객체를 생성하는 역할만 하는 생성자
	public NewTV() {
		
	}
	//모든 전역변수를 초기화하는 생성자
	public NewTV(int inch, boolean power, int channel, int volume) {
		this.inch = inch;
		this.power = power;
		this.channel = channel;
		this.volume = volume;
	}
	//기본생성자, 모든 매개변수를 초기화하는 생성자는 무조건 만들고 그 외 추가로 필요한걸 더 생성
	public NewTV(int inch) {
		this.inch = inch;
	}
	
	
	public int getInch() {
		return inch;
	}
	
	public boolean isPower() {
		return power;
	}
	
	public int getChannel() {
		return channel;
	}
	
	public int getVolume() {
		return volume;
	}
	
	
	
	public void setInch(int inch) {
		this.inch = inch;
	}
	
	public void setPower(boolean power) {
		this.power  = power;
	}
	
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
