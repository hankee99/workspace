package kh.java.func;

import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
	Random r;
	Scanner sc;
	
	public Lotto() {
		super();
		r = new Random();
		sc = new Scanner(System.in);
	}
	
	public void lottoExam() {
		HashSet<Integer> usernum = new HashSet<>();
		HashSet<Integer> lotto = new HashSet<>();
		
		System.out.println("==============로또=============");
		while(usernum.size()<6) {
			System.out.print(usernum.size() +1 +"번째 번호를 입력하세요: ");
			int num = sc.nextInt();
			if(num>45 || num<1) {
				System.out.println("1부터 45사이의 수를 입력해주세요.");
			}else if(!usernum.add(num)) {
				System.out.println("중복된 수입니다.");
			}
		}
		
		while(lotto.size()<6) {
			int num = r.nextInt(45) + 1;
			lotto.add(num);
		}
		
		ArrayList<Integer> uNum = new ArrayList<Integer>(usernum);
		ArrayList<Integer> lNum = new ArrayList<Integer>(lotto);
		uNum.sort(null);
		lNum.sort(null);
		
		int cnt = 0;
		for(int i=0; i<uNum.size(); i++) {
			for(int j=0; j<lNum.size(); j++) {
				if(uNum.get(i) == lNum.get(j)) {
					cnt++;
					break;
				}
			}
		}
		System.out.println("당첨 번호: " + lNum);
		System.out.println("나의 번호: " + uNum + "       맞은 갯수: " + cnt);
		if(cnt <6) {
			System.out.println("꽝!");
		}else if(cnt == 6) {
			System.out.println("당첨!");
		}
		
		System.out.println("종료되었습니다.....");
		
		
		sc.close();
	}
	
	
	

}
