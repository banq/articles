package com.example.order.repository;

import com.example.order.domain.Order;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {

	@Query("select count(*) from order_item")
	int countItems();
}
