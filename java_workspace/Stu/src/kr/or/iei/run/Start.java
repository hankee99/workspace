package kr.or.iei.run;
import kr.or.iei.func.Exam;
import java.util.ArrayList;
public class Start {

	public static void main(String[] args) {
		int n=15;
		
		ArrayList<Integer> arr = new ArrayList<>();
		int cnt =0;
        for(int i=1;i<=n;i++) {
        	int sum =0;
        	for(int j=i; j<=n; j++) {
        		sum += j;
        		
        		if(sum == n) {
        			cnt++;
        			break;
        		}else if(sum>n) {
        			break;
        		}
        		
        	}
        	
        }
        System.out.println(cnt);
        
	}

}
