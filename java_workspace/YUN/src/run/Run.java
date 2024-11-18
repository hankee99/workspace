package run;

import java.util.Arrays;

import yunsp.Yunspham;

public class Run {

	public static void main(String[] args) {
		String[] picture = {".x.","x..x","xx.xx"};
		int k= 3;
		
		String[] answer = new String[picture.length * k];

		for (int i = 0; i < picture.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < picture[i].length(); j++) {
				sb.append(String.valueOf(picture[i].charAt(j)).repeat(k));
			}

			for (int n = 0; n <=picture.length*k -k; n += k) {
				for (int s = 0; s < k; s++) {
					answer[s+n] = sb.toString();
				}
			}
		}

		System.out.println(Arrays.toString(answer));
	}

}
