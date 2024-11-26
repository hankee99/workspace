package kh.java.func;

public class TestClass {
	public void test1() {
		GenericEx3<Integer,String> ge3 = new GenericEx3<>();
		ge3.setData1(100);
		ge3.setData2("문자열");
		System.out.println(ge3.getData1());
		System.out.println(ge3.getData2());
		
	}

}
