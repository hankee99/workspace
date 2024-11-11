package bus;

public class Buss {
	
	 public int[] setArray() {
		 int[][] seat = {
				 {1,4},
				 {1,3},
				 {0,2}
		 };
		 
		 int[] arr = {1,2,4,3,5};
		 
		 for(int[] i : seat) {
			 int a = arr[i[0]];
			 int b = arr[i[1]];
			 
			 arr[i[0]] = b;
			 arr[i[1]] = a;
			 
		 }
		 
		 return arr;
		 
	 }

}
