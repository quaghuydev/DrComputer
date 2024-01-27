package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.id.quanghuydevfs.drcomputer.model.enums.Category;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;

@Repository
public interface ProductRepsitory extends JpaRepository<Product,Long> , PagingAndSortingRepository<Product,Long> {

    Product getProductsByTitle(String name);
    Product getProductsByCategory(Category category);
}
