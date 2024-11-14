package run;



import java.util.Scanner;

import yunsp.Yunspham;

public class Run {

	public static void main(String[] args) {
		//Yunspham ys = new Yunspham();
		
		String s = "a b C";
		int n = 4;
		char[] carr = s.toCharArray();
        for(char c : carr){
            if((c>=65 && c<=90) || (c>=97 && c<=122)){ //c가 알파벳일때
                if(((c+n) > 90) && ((c+n) <97)){       //대문자 c를 n만큼 밀었을때 90보다 크다면
                    c = (char)(c+n -26);                       //알파벳 갯수(26)만큼 뺀다
                }else if((c+n)>=65 && (c+n)<=90){      //아니라면 대문자 c를 n만큼 민다.
                    c += n;
                }else if((c+n)>122){                   //소문자 c를 n만큼 밀었을때 122보다 크다면
                    c= (char)(c+n -26);                        //알파벳 갯수(26)만큼 뺀다
                }else if(((c+n)>=97 && (c+n)<=122)){   //아니라면 소문자 c를 n만큼 민다.
                    c += n;
                }
            }
        }
        
        System.out.println(carr);
        

	}

}
