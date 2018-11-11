package com.example.order;

import com.example.order.domain.Address;
import com.example.order.domain.Order;
import com.example.order.domain.Owner;
import com.example.order.domain.OwnerRef;
import com.example.order.repository.OrderRepo;
import com.example.order.repository.OwnerRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	@Autowired
	OwnerRepo ownerRepo;

	@Autowired
	OrderRepo orderRepo;

	@Test
	public void contextLoads() {
		Owner owner = new Owner();
		owner.setName("my name");
		ownerRepo.save(owner);

		Order order = new Order();
		OwnerRef ownerRef = new OwnerRef();
		ownerRef.ownerId = owner.getId();
		order.ownerRef = ownerRef;

		Address address = new Address();
		address.street = "a street";
		address.zipcode = "123456789";
		order.address = address;

		order.addItem(1, "\"this is product1111\"");
		order.addItem(2, "\"this is product2222\"");
		Order ordersave = orderRepo.save(order);

		assertThat(ordersave.address.zipcode.equalsIgnoreCase("123456789"));
		assertThat(orderRepo.countItems() == 2);

		Order findOrder = orderRepo.findById(ordersave.Id).orElse(new Order());
		assertThat(findOrder.items.size() == 2);

		orderRepo.deleteAll();

		assertThat(orderRepo.countItems() == 0);
		assertThat(ownerRepo.count() == 1);


	}


}
