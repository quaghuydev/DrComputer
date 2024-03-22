package vn.id.quanghuydevfs.drcomputer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.controller.order.OrderResponse;
import vn.id.quanghuydevfs.drcomputer.dto.order.OrderDto;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderDetail;
import vn.id.quanghuydevfs.drcomputer.model.user.User;
import vn.id.quanghuydevfs.drcomputer.repository.OrderDetailRepository;
import vn.id.quanghuydevfs.drcomputer.repository.OrderRepository;
import vn.id.quanghuydevfs.drcomputer.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository repository;
    private final OrderDetailRepository detailRepository;
    private final UserRepository userRepository;

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public OrderResponse create(OrderDto o) {
        List<OrderDetail> orderDetails = o.getOrderDetails().stream()
                .map(orderDetailDto -> OrderDetail.builder()
                        .product(orderDetailDto.getProduct())
                        .quantity(orderDetailDto.getQuantity())
                        .subtotal(orderDetailDto.getSubtotal())
                        .build())
                .toList();
        Optional<User> user = userRepository.findByEmail(o.getUser().getEmail());
        Order order = Order.builder()
                .customer(o.getCustomer())
                .street(o.getStreet())
                .province(o.getProvince())
                .district(o.getDistrict())
                .ward(o.getWard())
                .user(user.get())
                .build();
        // Lưu Order vào cơ sở dữ liệu để tạo order_id
        repository.save(order);
        // Thiết lập order cho từng OrderDetail
        orderDetails.forEach(orderDetail -> orderDetail.setOrder(order));
        detailRepository.saveAll(orderDetails);

        // Lưu danh sách OrderDetails vào cơ sở dữ liệu

        return OrderResponse.builder()
                .order(order)
                .orderDetails(orderDetails)
                .user(o.getUser())
                .build();
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

