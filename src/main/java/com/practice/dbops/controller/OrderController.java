package com.practice.dbops.controller;

import com.practice.dbops.exception.ResourceNotFoundException;
import com.practice.dbops.model.Item;
import com.practice.dbops.model.Order;
import com.practice.dbops.repository.ItemRepository;
import com.practice.dbops.repository.OrderRepository;
import com.practice.dbops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/user/{userId}/orders")
    public List<Order> getAllOrdersByPostId(@PathVariable(value = "userId") Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @PostMapping("/user/{userId}/orders")
    public Order createOrder(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Order order) {
        return userRepository.findById(userId).map(user -> {
            order.setUser(user);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/user/{userId}/orders/{orderId}")
    public Order updateOrder(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "orderId") Long orderId,
                                 @Valid @RequestBody Order orderRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return orderRepository.findById(orderId).map(order ->
                orderRepository.save(order)).orElseThrow(() ->
                new ResourceNotFoundException("OrderId " + orderId + "not found"));
    }

    @DeleteMapping("/user/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "orderId") Long orderId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + orderId + " not found"));
    }

    @GetMapping("/order/{orderId}")
    public List<Item> getAllItems(@PathVariable(value = "orderId") Long orderId) {
        return itemRepository.findItemsByOrderId(orderId);
    }
}
