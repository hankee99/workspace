package excc;


import java.util.ArrayList;
import java.util.Arrays;


public class EXCCCC {

	public static void main(String[] args) {
		
		String str1 = "AAAbbcc";
		String str2 = "Abbc";
		
String ex = str1;
        
        String regex = "[^" + str2 + "]";
        
        ex = ex.replaceAll(regex,"");
        
        if(ex.trim().equals(str2)){
            System.out.println(1);
        }else{
        	System.out.println(2);
        }
        System.out.println(ex);
	}

}
