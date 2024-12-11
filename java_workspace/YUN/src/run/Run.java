package run;

import java.util.ArrayList;
import java.util.Arrays;

import yunsp.Yunspham;

public class Run {

	public static void main(String[] args) {
		ArrayList<String> lst = new ArrayList<>();
        int n = 4;
        
        for(int i=0; i<n; i++){
            String str = String.valueOf(i+1).repeat(i+1);
            for(int j=1; j<n-i; j++){
                str += String.valueOf(i+j+1);
            }
            lst.add(str);
        }
        
        for(String s : lst) {
        	System.out.println(s);
        }
	}

}
