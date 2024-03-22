package vn.id.quanghuydevfs.drcomputer.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.dto.order.OrderDto;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;
import vn.id.quanghuydevfs.drcomputer.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrders() {
        var orders = service.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable UUID id) {
        var order = service.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(service.create(orderDto));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOrder(UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
