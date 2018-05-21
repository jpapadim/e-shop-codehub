package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.service.CartService;
import com.codehub.spring.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "e-shop/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;


//    @PostMapping(consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(orderService.saveOrder(order));
//    }

    @PostMapping(value = "create")
    public ResponseEntity<Order> createOrder(@RequestParam("userId") Long userId) {

        List<CartItem> cartItems = new ArrayList<>(cartService.findAll(userId));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.checkout(cartItems));
    }



    @PutMapping(value = "updatestatus/{order_id}")
    public ResponseEntity<Order> updateOrderStatus(@RequestParam(value = "order_id") Integer orderId,
                                                   @RequestParam(value = "order_status") OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity
                .ok()
                .body(orderService.findOrderById(orderId).get());
    }


    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "orderId") Integer orderId) {
        Optional<Order> order = orderService.findOrderById(orderId);
        return order.map(order1 -> ResponseEntity
        .ok()
        .body(order1)).orElseGet(() -> ResponseEntity
        .notFound()
        .build());
    }
}