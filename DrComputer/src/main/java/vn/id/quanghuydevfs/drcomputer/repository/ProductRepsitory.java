package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
@Repository
public interface ProductRepsitory extends JpaRepository<Product,Long> {
}
