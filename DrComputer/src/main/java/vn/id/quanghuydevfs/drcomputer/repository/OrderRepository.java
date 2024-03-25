package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
