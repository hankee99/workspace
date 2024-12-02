package kh.java.func;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StreamTest {
	public void test1() {
		
		BufferedReader br = null;
		BufferedOutputStream bos = null;
		
		try {
			FileReader fr = new FileReader("quiz.txt");
			br = new BufferedReader(fr);
			System.out.println("읽기 전");
			String str = br.readLine();
			System.out.println("읽기 후");
			ArrayList<Byte> lst = new ArrayList<>();
			StringTokenizer sT = new StringTokenizer(str," ");
			
			while(sT.hasMoreTokens()) {
				String num = sT.nextToken();
				int iNum = Integer.parseInt(num);
				lst.add((byte)iNum);
			}
			System.out.println(lst.size());
			
			FileOutputStream fos = new FileOutputStream("quiz.gif");
			bos = new BufferedOutputStream(fos);
			
			System.out.println("파일 내보내기 시작");
			for(byte b : lst) {
				bos.write(b);
			}
			System.out.println("파일 내보내기 끝");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
