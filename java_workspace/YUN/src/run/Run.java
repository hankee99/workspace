package run;



import java.util.*;

import yunsp.Yunspham;

public class Run {

	public static void main(String[] args) {
		String[] strings = {"sun", "bed", "car"};
		int n =1;
		String[] str = new String[strings.length];  //strings의 요소들의 n번째 알파벳을 담을 배열
        for(int i=0; i<strings.length; i++){
            str[i] = String.valueOf(strings[i].charAt(n));
        }
        Arrays.sort(str); // 알파벳들을 정렬
        HashSet<String> set = new HashSet<>();
        //String[] ans = new String[str.length];
        System.out.println(Arrays.toString(str));
        for(int i=0; i<str.length; i++){
            for(int j=0; j<str.length; j++){
                if(str[i].equals(String.valueOf(strings[j].charAt(n))) ){
                    set.add(strings[j]);
                }
            }
        }
        
        
        //Arrays.sort(ans);
        
        

	}

}
