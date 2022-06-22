package com.example.bes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
	private String account;
	private String goodid;
	private String name;
	private String price;
	private String discount;
	private String details;
	private String oan;
	private String type;
	private String model;
	private String counteroffer;
	private String stock;
	private String imgUrl;
	private String identity;
	private String sold;
}
