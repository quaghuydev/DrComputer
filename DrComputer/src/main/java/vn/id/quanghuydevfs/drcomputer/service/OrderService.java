package vn.id.quanghuydevfs.drcomputer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.dto.order.OrderDto;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;
import vn.id.quanghuydevfs.drcomputer.repository.OrderDetailRepository;
import vn.id.quanghuydevfs.drcomputer.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository repository;
    private final OrderDetailRepository detailRepository;

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order create(OrderDto o) {
        var order = Order.builder()
                .Customer(o.getCustomer())
                .street(o.getStreet())
                .province(o.getProvince())
                .district(o.getDistrict())
                .ward(o.getWard())
                .orderDetails(o.getOrderDetails())
                .build();
        return repository.save(order);
    }

    public Order getOrderById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Boolean delete(UUID id) {
        var order = repository.findById(id).orElse(null);
        if (order != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}

