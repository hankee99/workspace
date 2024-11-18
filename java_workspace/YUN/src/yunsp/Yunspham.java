package yunsp;

import java.util.*;

public class Yunspham {
	
	public void mode() {   //배열에서 최빈값 찾기
		int[] arr = {0,1,1,2,3,3,4};
		
		int ans = 0;
		int arrMax =0;
		int cntMax =0;
		
		for(int i=0; i<arr.length; i++) {
			if(arrMax < arr[i]) {
				arrMax = arr[i];
			}
		}
		
		int[] copyarr = new int[arrMax +1];
		
		for(int i=0; i<arr.length; i++) {
			copyarr[arr[i]]++;
		}
		
		for(int i=0; i<copyarr.length; i++) {
			if(cntMax < copyarr[i]) {
				cntMax = copyarr[i];
				ans = i;
			}
		}
		
		int cnt = 0;
		for(int i=0; i<copyarr.length; i++) {
			if(cntMax == copyarr[i]) {
				cnt++;
			}
		}
		
		if(cnt>1) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
		
		
	}
	
	public int par() {
		int[][] xy = {
				{2,1},
				{4,10},
				{5,7},
				{7,12}
		};
		
		ArrayList<Double> arr = new ArrayList<>();
		double[] cparr = new double[6];
		
		for(int i=0; i<3; i++) {   //4개 좌표중 2개 (4C2) 6개 기울기 계산
			for(int j=0; j<3-i; j++) {
				arr.add((double)(xy[i][1] - xy[j+i+1][1])/(xy[i][0]-xy[j+i+1][0]));
			}
		}
		
		for(int i=0; i<6; i++){   //arr리스트를 배열cparr에 복사
            cparr[i] = arr.get(i);
        }
		
		Arrays.sort(cparr); //기울기들을 오름차순으로 정렬
		
		System.out.println(Arrays.toString(cparr));
		
		for(int i=0; i<5; i++) {  //중복되는 기울기가 있으면 1반환
			if(cparr[i] == cparr[i+1]) {
				return 1;
			}
		}
		return 0;    //없으면 0반환
		
	}
	
	public int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	
	public int lcm(int a, int b) {
		return (a*b)/gcd(a,b);
	}
	
	
	
	

}
