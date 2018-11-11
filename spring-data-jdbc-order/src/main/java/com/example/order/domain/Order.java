package com.example.order.domain;


import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Collection;

@Table("ordersample")
public class Order {
	@org.springframework.data.annotation.Id
	public Integer Id;

	public Address address;

	public OwnerRef ownerRef;

	public Collection<OrderItem> items;

	public Order() {
		items = new ArrayList<>();
	}

	public void addItem(int quantity, String productId) {
		items.add(createOrderItem(quantity, productId));
	}

	private OrderItem createOrderItem(int quantity, String productId) {
		OrderItem orderItem = new OrderItem();
		orderItem.productVO = productId;
		orderItem.qty = quantity;
		return orderItem;
	}


}