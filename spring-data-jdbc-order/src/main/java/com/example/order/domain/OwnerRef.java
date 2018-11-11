package com.example.order.domain;


import org.springframework.data.relational.core.mapping.Table;

@Table("Order_Owner")
public class OwnerRef {

	@org.springframework.data.annotation.Id
	public Integer Id;
	public Integer ownerId;


}