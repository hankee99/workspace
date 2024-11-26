package kh.java.func;

public class ObjectTest {
	public void test1() {
		TestClass2 tc1 = new TestClass2("hi",100);
		
		
		System.out.println(tc1);
		
		
		try {
			Object obj = tc1.clone();
			TestClass2 tc4 = (TestClass2)obj;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test2() {
		TestClass2 tc1 = new TestClass2();
		TestClass2 tc2 = new TestClass2();
		TestClass2 tc3 = tc2;
		
		boolean result1 = tc1.equals(tc2);
		boolean result2 = tc2.equals(tc3);
		boolean result3 = tc3.equals(tc1);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		
		String str1 = "hi";
		String str2 = "hi";
		String str3 = str2;
		boolean result4 = (str1 == str2);
		System.out.println(result4);
	}

}
