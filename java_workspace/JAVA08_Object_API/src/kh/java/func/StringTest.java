package kh.java.func;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTest {
	
	public void test3() {
		String str = "전한기/서울/학생/KH";
		StringTokenizer st = new StringTokenizer(str,"/");
		System.out.println("현재 토큰 수: " + st.countTokens());
		System.out.println(st.nextToken());
		System.out.println(st.nextToken());
		System.out.println(st.nextToken());
		
		System.out.println("현재 토큰 수: " + st.countTokens());
		
	}

}
