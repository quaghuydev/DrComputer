package vn.id.quanghuydevfs.drcomputer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.id.quanghuydevfs.drcomputer.controller.order.OrderResponse;
import vn.id.quanghuydevfs.drcomputer.dto.log.LogReqDTO;
import vn.id.quanghuydevfs.drcomputer.dto.order.OrderDto;
import vn.id.quanghuydevfs.drcomputer.dto.product.ProductItemDto;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.exception.ResourceNotFoundException;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderItem;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderItemPK;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.model.user.User;
import vn.id.quanghuydevfs.drcomputer.repository.OrderDetailRepository;
import vn.id.quanghuydevfs.drcomputer.repository.OrderRepository;
import vn.id.quanghuydevfs.drcomputer.repository.ProductRepository;
import vn.id.quanghuydevfs.drcomputer.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderDetailRepository detailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LogService logService;

    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Transactional
    public OrderResponse createOrder(OrderDto orderDto) throws ResourceNotFoundException {
        BigDecimal totalAmount = BigDecimal.ZERO;
        User user = userRepository.findByEmail(orderDto.getUser().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = Order.builder()
                .fullname(orderDto.getFullname())
                .user(user)
                .street(orderDto.getStreet())
                .ward(orderDto.getWard())
                .district(orderDto.getDistrict())
                .province(orderDto.getProvince())
                .note(orderDto.getNote())
                .numberHouse(orderDto.getNumberHouse())
                .isPaied(orderDto.isPaied())
                .paymentMethod(orderDto.getPayMethod())
                .status(0)
                .createdAt(LocalDate.now())
                .orderItems(new ArrayList<>())
                .updatedAt(LocalDate.now())
                .build();

        // Save the Order immediately to ensure it has an ID.
        order = repository.save(order);

        for (ProductItemDto p : orderDto.getProducts()) {
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            OrderItem orderItem = OrderItem.builder()
                    .id(new OrderItemPK(order.getId(), product.getId()))
                    .order(order)
                    .product(product)
                    .price(product.getPrice())
                    .quantity(p.getQuantity())
                    .build();

            order.addOrderItem(orderItem);

            totalAmount = totalAmount.add(
                    BigDecimal.valueOf(
                            calculatePriceAfterSale(product.getSale(), product.getPrice()) * p.getQuantity()
                    )
            );
        }

        order.setTotalAmount(totalAmount);
        order = repository.save(order);
        logService.insertLog(LogReqDTO.builder().user(UserDto.builder().email(user.getEmail()).fullname(user.getFullname()).phoneNumber(user.getPhoneNumber()).role(user.getRoles()).build()).content("create order "+order.getId()).build());
        return OrderResponse.builder()
                .user(orderDto.getUser())
                .order(order)
                .build();// Save the Order again, now with its OrderItems.
    }


    public static long calculatePriceAfterSale(double sale, long price) {
        return (long) (price - price * (sale / 100));
    }

    public Order getOrderById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Boolean delete(long id) {
        var order = repository.findById(id).orElse(null);

        if (order != null) {
            var user = order.getUser();
            logService.insertLog(LogReqDTO.builder().user(UserDto.builder().email(user.getEmail()).fullname(user.getFullname()).phoneNumber(user.getPhoneNumber()).role(user.getRoles()).build()).content("delete order "+order.getId()).build());
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }


    }

    public OrderItem getOrderDetailById(long id) {
        return detailRepository.findById(id).orElse(null);
    }

    public static void main(String[] args) {
        System.out.println(calculatePriceAfterSale(0.15, 50000));
    }
}

