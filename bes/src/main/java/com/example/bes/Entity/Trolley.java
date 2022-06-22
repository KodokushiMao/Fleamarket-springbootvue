package com.example.bes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trolley {

	private String account;
	private String shopaccount;
	private String goodid;
	private String name;
	private String price;
	private String discount;
	private String details;
	private String oan;
	private String type;
	private String model;
	private String counteroffer;
	private String num;
	private String stock;
	private String imgUrl;
	private String identity;
	private String time;
	private String phone;
	private String username;
	private String address;
}
