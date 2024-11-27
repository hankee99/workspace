package kh.java.func;

import java.util.Arrays;

public class NewArray<T> {
	private Object[] arr;
	private int index;
	public NewArray() {
		super();
		
		arr = new Object[10];
		index =0;
	}
	
	public int size() { //리스트 크기 반환
		return index;
	}
	
	public void add(T a) { //리스트 맨 뒤에 요소 추가
		if(index == arr.length) {
			Object[] newArr = new Object[arr.length*2];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		
		arr[index++] = a;
	}
	
	public void add(int n, T a) { //n번 인덱스에 a삽입
		if(index == arr.length) {
			Object[] newArr = new Object[arr.length*2];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		
		for(int i=index; i>n; i--) {
			arr[i] = arr[i-1];
		}
		
		arr[n] = a;
		index++;
	}
	
	public T set(int n, T a) { //n번 인덱스 a로 수정
		T b = (T)arr[n];
		if(n<index) {
			arr[n] = a;
			return b;
		}else {
			throw new IndexOutOfBoundsException();
		}
		
		
	}
	
	public T remove(int a) { //인덱스로 지우기
		T b = (T)arr[a];
		for(int i =a; i<index-1; i++) {
			arr[i] = arr[i+1];
		}
		arr[index-1] = null;
		index--;
		return b;
	}
	
	public boolean remove(T a) { //요소로 지우기
		for(int i=0; i<index; i++) {
			if(a.equals(arr[i])) {
				for(int j=i; j<index-1; j++) {
					arr[j] = arr[j+1];
				}
				arr[index-1] = null;
				index--;
				return true;
			}
		}
		return false;
	}
	
	public T get(int a) { //a번 인덱스의 요소 반환
		if(a<index) {
			return (T)arr[a];
		}else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	public boolean isEmpty() { //리스트가 비어있는지 확인
		return (index == 0);
	}
	
	public boolean contains(T a) { //요소 a가 리스트 안에 있는지 확인
		for(int i=0; i<index; i++) {
			if(arr[i].equals(a)) return true;
		}
		return false;
	}
	
	public void clear() { //리스트를 완전히 비움
		for(int i=0; i<index; i++) {
			arr[i] = null;
		}
		index = 0;
	}
	
	public int indexOf(T a) { //a요소가 처음 등장하는 인덱스 반환
		for(int i=0; i<index; i++) {
			if(a.equals(arr[i])) return i;
		}
		
		return -1;
	}
	
	
	public int lastIndexOf(T a) {  //a요소가 마지막으로 등장하는 인덱스 반환
		for(int i=index-1; i>=0; i--) {
			if(a.equals(arr[i])) return i;
		}
		return -1;
	}
	
	public void sort() { // 정렬 메소드
		Object[] newArr = new Object[size()];
		System.arraycopy(arr, 0, newArr, 0, newArr.length);
		Arrays.sort(newArr);
		for(int i=0; i<size(); i++) {
			arr[i] = newArr[i];
		}
	}
	
	public Object[] toArray() { //리스트를 배열로 만들어주는 메소드
		Object[] newArr = new Object[size()];
		System.arraycopy(arr, 0, newArr, 0, size());
		
		return newArr;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i=0; i<size(); i++) {
			sb.append(arr[i]);
			sb.append(" ");
		}
		sb.append("}");
		
		return sb.toString();
	}
	

}
