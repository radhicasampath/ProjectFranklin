package com.practice.dbops;

import com.practice.dbops.model.Item;
import com.practice.dbops.model.Order;
import com.practice.dbops.model.User;
import com.practice.dbops.repository.ItemRepository;
import com.practice.dbops.repository.OrderRepository;
import com.practice.dbops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DbOpsApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DbOpsApplication.class);
	}

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(DbOpsApplication.class, args);

		UserRepository userRepository =
				context.getBean("userRepository", UserRepository.class);

		User user1 = new User();
		User user2 = new User();
		userRepository.save(user1);
		userRepository.save(user2);

		OrderRepository orderRepository =
				context.getBean("orderRepository", OrderRepository.class);

		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		user1.addOrder(order1);
		user1.addOrder(order2);
		user2.addOrder(order3);

		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);

		Item item1 = new Item();
		Item item2 = new Item();

		ItemRepository itemRepository =
				context.getBean("itemRepository", ItemRepository.class);
		itemRepository.save(item1);
		itemRepository.save(item2);

	}

}
