package kr.co.iei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
	private String name;
	private int age;
	private String addr;
	
	public String getTestData() {
		return "hi";
	}
}
