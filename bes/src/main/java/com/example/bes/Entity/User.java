package com.example.bes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String account;
	private String password;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String city;
	private String bankcard;
	private String identity;
	private String licence;
	private String idcard;
	private String remainder;
	private String level;
	private String grade;
}
