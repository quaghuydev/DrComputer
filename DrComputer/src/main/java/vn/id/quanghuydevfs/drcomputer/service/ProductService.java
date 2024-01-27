package vn.id.quanghuydevfs.drcomputer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.model.enums.Category;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.repository.ProductRepsitory;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepsitory productRepository;


    public Page<Product> getProducts(int page, int size, String sort) {
        Sort s ;
        if(sort.equals("id")){
            s = Sort.by(Sort.Order.asc("id"));
        }else{
            s = Sort.by(sort.equals("asc") ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        }
        Pageable pageable = PageRequest.of(page, size, s);
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product getProductByName(String name) {
        return productRepository.getProductsByTitle(name);
    }
    public Product getProductByCategory(Category category) {
        return productRepository.getProductsByCategory(category);
    }
}
