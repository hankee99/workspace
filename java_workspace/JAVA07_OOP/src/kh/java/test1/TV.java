package kh.java.test1;

import java.util.Scanner;

public class TV {
	
	public int inch;
	public boolean power;
	public int vol;
	public int chan;
	
	public void powerOnOff() {
		power = !power;
		if(power) {
			System.out.println("전원켜짐");
		}else {
			System.out.println("전원꺼짐");
		}
	}
	
	public void channelUp() {
		chan++;
		System.out.println("현재 채널: " + chan);
	}
	
	public void channelDown() {
		chan--;
		System.out.println("현재 채널: " + chan);
	}
	
	public void volumeUp() {
		vol++;
		System.out.println("현재 볼륨: " + vol);
	}
	
	public void volumeDown() {
		vol--;
		System.out.println("현재 볼륨: " + vol);
	}
	
	public void tvStart() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("=============TV==============");
			System.out.println("1. 전원버튼");
			System.out.println("2. 채널 올리기");
			System.out.println("3. 체널 내리기");
			System.out.println("4. 볼륨 올리기");
			System.out.println("5. 볼륨 내리기");
			System.out.println("0. TV버리기");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
			case 1:
				powerOnOff();
				break;
			case 2:
				if(power) channelUp();
				break;
			case 3:
				if(power) channelDown();
				break;
			case 4:
				if(power) volumeUp();
				break;
			case 5:
				if(power) volumeDown();
				break;
			case 0:
				return;
			}
		}
		
	}

}
