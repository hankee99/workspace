package hi;

import java.util.*;

public class Cacheee {
	
	public void ListExam() {
		Scanner sc = new Scanner(System.in);
		LinkedList<String> cache = new LinkedList<String>();
		System.out.println("캐시 크기를 입력하시오(0~30): ");
		int cacheSize = sc.nextInt();
		
//		String [] arr = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//		String [] arr = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
//		String [] arr = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
//		String [] arr = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
//		String [] arr = {"Jeju", "Pangyo", "NewYork", "newyork"};
		String [] arr = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		int sum = 0;
		
		for(int i=0; i<arr.length; i++) {
			if(!cache.removeFirstOccurrence(arr[i].toLowerCase())) { //캐시에 값이 없으면
				sum += 5;
				cache.addFirst(arr[i].toLowerCase());
				if(cache.size()>cacheSize) cache.removeLast();
			}else { //이미 값이 있으면
				sum++;
				cache.addFirst(arr[i].toLowerCase());
			}
		}
		
		System.out.println("실행시간: " + sum);
		
		sc.close();
	}
	
	public void mapExam() {
		int n =5;
		int[] arr1 = {9,20,28,18,11};
		int[] arr2 = {30,1,21,17,28};
		
		int[][] deepArr1 = new int[n][n];
		int[][] deepArr2 = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				String s = "0".repeat(n-Integer.toBinaryString(arr1[i]).length()) + Integer.toBinaryString(arr1[i]);
				deepArr1[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				String s = "0".repeat(n-Integer.toBinaryString(arr2[i]).length()) + Integer.toBinaryString(arr2[i]);
				deepArr2[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		
		
		
		int[][] ansArr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(deepArr1[i][j] == 0 && deepArr2[i][j] == 0) {
					ansArr[i][j] = 0;
				}else {
					ansArr[i][j] = 1;
				}
			}
		}
		
		
		
		String[][] str = new String[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				str[i][j] = String.valueOf(ansArr[i][j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(str[i][j] + "\t");
			}
			System.out.println();
		}
		
		
		String[] lst = new String[n];
		for(int i=0; i<n; i++) {
			lst[i] = String.join("", str[i]).replaceAll("1","#").replaceAll("0"," " );
		}
		
		System.out.println("\n-----------------------정답------------------------------");
		for(int i=0; i<n; i++) {
			System.out.print("|"+lst[i] + "|\t");
		}
		
		
	}
	
	

}
