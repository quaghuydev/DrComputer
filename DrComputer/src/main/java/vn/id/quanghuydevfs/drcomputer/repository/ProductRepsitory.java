package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.id.quanghuydevfs.drcomputer.model.product.Category;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;

import java.util.List;

@Repository
public interface ProductRepsitory extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    Page<Product> getProductsByTitleContainsIgnoreCase(String name, Pageable pageable);

    Page<Product> findByCategoryAndTitleContainingIgnoreCase(Category category, String keyword, Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);

}
