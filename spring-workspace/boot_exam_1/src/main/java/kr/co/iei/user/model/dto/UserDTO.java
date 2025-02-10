package kr.co.iei.user.model.dto;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

	private int userNo;

	private String userName;

	private int userAge;

	private String userGender;

	private String bloodType;

}