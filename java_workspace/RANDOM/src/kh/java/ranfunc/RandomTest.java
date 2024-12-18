package kh.java.ranfunc;

import java.util.*;

public class RandomTest {
	public void test1() {
		Random r = new Random();
		int num1 = r.nextInt(); //int범위 내에서 랜덤숫자 가져옴(경우의 수 많아서 활용 어려움)
		System.out.println("랜덤수: " + num1);
		
		int num2 = r.nextInt(10); //숫자10개(0~9)중 랜덤으로 1개
		System.out.println("랜덤수: " + num2);
		
		int num3 = r.nextInt(13);
		System.out.println("랜덤수: " + num3);
		
		int num4 = r.nextInt(27);
		System.out.println("랜덤수: " + num4);
		
		int num5 = r.nextInt(11) -5;  // -5<= num5 <=5    r.nextInt(경우의 수) + 시작숫자
		System.out.println("랜덤수: " + num5);
		
		int num6 = r.nextInt(21) + 10;
		System.out.println("랜덤수: " + num6);
		
		
		for(int i=0; i<5; i++) {   //랜덤 문자열 생성
			char num7 = (char)(r.nextInt(26) + 65);
			System.out.print(num7 + "");
		}
		
	}
	
	public void exam1() {
		StringBuilder sb = new StringBuilder();
		Random r =new Random();
		Scanner sc = new Scanner(System.in);
		boolean t = true;
		int win = 0;
		int lose = 0;
		while(t) {
			System.out.println("===========동전 앞뒤맞추기============");
			System.out.println("1. 게임시작");
			System.out.println("2. 전적보기");
			System.out.println("0. 게임종료");
			System.out.print("선택 >> ");
			
			int select = sc.nextInt();
			System.out.println();
			switch(select) {
			case 1:
				int coin = r.nextInt(2) + 1;
				
				System.out.print("앞(1),뒤(2) 중 선택하시오: ");
				int select2 = sc.nextInt();
				System.out.println("정답: " + (coin == 1?"앞":"뒤"));
				
				if(select2 == coin) {
					System.out.println("승\n");
					sb.append("승\n");
					win++;
				}else{
					System.out.println("패\n");
					sb.append("패\n");
					lose++;
				}
				break;
			case 2:
				System.out.println("====전적====");
				System.out.print(sb);
				System.out.printf("%d승 %d패\n\n",win,lose);
				break;
			case 0:
				t =false;
				break;
			default:
				System.out.println("잘못입력함.");
				break;
			}
		}
		
		
		System.out.println("종료....");
		
		
		sc.close();
	}
	
	
	public void exam2() {
		StringBuilder sb = new StringBuilder();
		Random r =new Random();
		Scanner sc = new Scanner(System.in);
		boolean t = true;
		int win = 0;
		int lose = 0;
		int draw = 0;
		while(t) {
			System.out.println("===========가위바위보============");
			System.out.println("1. 게임시작");
			System.out.println("2. 전적보기");
			System.out.println("0. 게임종료");
			System.out.print("선택 >> ");
			
			int select = sc.nextInt();
			
			System.out.println();
			switch(select) {
			case 1:
				int com = r.nextInt(3) + 1;
				
				System.out.print("가위(1),바위(2),보(3) 중 선택하시오: ");
				int select2 = sc.nextInt();
				if(com == 1) {
					System.out.println("컴퓨터: 가위");
				}else if(com ==2) {
					System.out.println("컴퓨터: 바위");
				}else if(com ==3) {
					System.out.println("컴퓨터: 보");
				}
				
				if(select2 == com) {
					System.out.println("비겼습니다.\n");
					sb.append("무 ");
					draw++;
				}else if((com == 1 && select2 == 3) || (com == 2 && select2 == 1) || (com == 3 && select2 == 2)){
					System.out.println("패배하였습니다.\n");
					sb.append("패 ");
					lose++;
				}else if((com == 1 && select2 == 2) || (com == 2 && select2 == 3) || (com == 3 && select2 == 1)){
					System.out.println("승리하였습니다.\n");
					sb.append("승 ");
					win++;
				}
				break;
			case 2:
				System.out.println("========전적========");
				System.out.println(sb);
				System.out.printf("%d승 %d무 %d패\n",win,draw,lose);
				System.out.println("===================\n");
				break;
			case 0:
				t =false;
				break;
			default:
				System.out.println("잘못입력함.");
				break;
			}
		}
		
		
		System.out.println("종료....");
		
		
		sc.close();
	}
	
	public void exam3() {
		Scanner sc = new Scanner(System.in);
		int[] usernum = new int[6];
		int[] lotto = new int[6];
		Random r = new Random();
		System.out.println("==============로또=============");
		for(int i=0; i<6; i++) {
			int num;
			while(true) {
				System.out.print(i+1 + "번째 번호를 입력하세요: ");
				num = sc.nextInt();
				if(num>45 || num<1) {
					System.out.println("1부터 45사이의 수를 입력해주세요.");
				}else if(contains(usernum,num,i)) {
					System.out.println("중복된 수입니다.");
				}else {
					usernum[i] = num;
					break;
				}
						
			}
			
		}
		
		int cnt =0;
		while(cnt<6) {
			int num = r.nextInt(45) + 1;
			if(!contains(lotto,num,cnt)) {
				lotto[cnt] = num;
				
				cnt++;
			}
		}
		Arrays.sort(usernum);
		Arrays.sort(lotto);
		System.out.print("당첨번호: ");
		for(int i =0; i<6; i++) {
			System.out.print(lotto[i] + "\t");
		}
		System.out.println();
		System.out.print("나의번호: ");
		for(int i =0; i<6; i++) {
			System.out.print(usernum[i] + "\t");
		}
		int inc=0;
		for(int i=0; i<6;i++) {
			for(int j=0; j<6; j++) {
				if(lotto[i] == usernum[j]) {
					inc++;
				}
			}
		}
		
		System.out.printf("맞은개수: %d\n",inc);
		
		if(inc == 6) {
			System.out.println("당첨입니다!");
		}else {
			System.out.println("꽝입니다!");
		}
		
		
		sc.close();
	}

	private boolean contains(int[] usernum, int num, int i) {
		for(int k=0; k<=i; k++) {
			if(num == usernum[k]) return true;
		}
		return false;
	}

}
