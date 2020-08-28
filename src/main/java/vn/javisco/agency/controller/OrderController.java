package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.javisco.agency.entity.Order;
import vn.javisco.agency.request.OrderCreateRequest;
import vn.javisco.agency.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @GetMapping
    public ResponseEntity<List<Order>> get() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> detail(@PathVariable("id") int id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderCreateRequest request) {
        Order order = orderService.create(request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@RequestBody OrderCreateRequest request, @PathVariable("id") int id) {
        Order newOrder = orderService.update(request, id);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        orderService.deleteById(id);
    }
}
