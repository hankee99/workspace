package kh.java.run;

import java.util.*;

import kh.java.func.NewArray;


public class Start {

	public static void main(String[] args) {
		NewArray<String> lst = new NewArray<>();
		lst.add("a");
		lst.add("b");
		lst.add("c");
		lst.add("d");
		lst.add("e");
		lst.add(2,"zz");
		lst.add("f");
		lst.add("b");
		System.out.println(lst.toString());
		System.out.println(lst.set(6, "B"));
		System.out.println(lst.toString());
		System.out.println(lst.remove("A"));
		System.out.println(lst.toString());
		lst.sort();
		System.out.println(lst.toString());
		
		//System.out.println(String.join("", lst.toArray()));
		
		ArrayList<String> alst = new ArrayList<String>();
		alst.add("e");
		alst.add("d");
		alst.add("c");
		alst.add("b");
		alst.add("a");
		alst.sort(Comparator.naturalOrder());
		System.out.println(alst.toString());
	}

}
