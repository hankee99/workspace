package kr.co.ie.point.start;

import kr.co.ie.point.controller.PointControllerImpl;

public class Start {

	public static void main(String[] args) {
		PointControllerImpl pc = new PointControllerImpl();
		//pc.main();
		
		String s = "abcd";
		int a =s.indexOf('a');
		System.out.println(a);

	}

}
