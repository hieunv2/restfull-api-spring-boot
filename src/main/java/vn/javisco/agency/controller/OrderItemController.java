package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.javisco.agency.entity.OrderItem;
import vn.javisco.agency.request.OrderItemRequest;
import vn.javisco.agency.services.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService service) {
        this.orderItemService = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> get() {
        return new ResponseEntity<>(orderItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> detail(@PathVariable("id") int id) {
        OrderItem order = orderItemService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemRequest request) {
        OrderItem order = orderItemService.create(request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(@RequestBody OrderItemRequest request, @PathVariable("id") int id) {
        OrderItem newOrder = orderItemService.update(request, id);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        orderItemService.deleteById(id);
    }
}
