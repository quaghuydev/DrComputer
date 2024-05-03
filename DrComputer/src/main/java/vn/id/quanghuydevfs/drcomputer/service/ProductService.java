package vn.id.quanghuydevfs.drcomputer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.id.quanghuydevfs.drcomputer.dto.log.LogReqDTO;
import vn.id.quanghuydevfs.drcomputer.dto.product.ProductDto;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.product.Category;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final LogService logService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Page<Product> getProducts(int page, int size, String sort, String category, String search) {
        Sort sorting;
        if (sort.equals("id")) {
            sorting = Sort.by(Sort.Order.asc("id"));
        } else {
            sorting = Sort.by(sort.equals("asc") ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        }

        Pageable pageable;
        if ("all".equalsIgnoreCase(category)) {
            pageable = PageRequest.of(page - 1, size, sorting);
        } else {
            pageable = PageRequest.of(page - 1, size, sorting);
            if (search != null && !search.isEmpty()) {
                return productRepository.findByCategory_ValueContainingIgnoreCaseAndTitleContainingIgnoreCase(category, search, pageable);
            } else {
                return productRepository.findByCategory_Value(category, pageable);
            }
        }

        if (search != null && !search.isEmpty()) {
            return productRepository.getProductsByTitleContainsIgnoreCase(search, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> getProductByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return productRepository.getProductsByTitleContainsIgnoreCase(name, pageable);
    }

    public Product add(ProductDto p) {
        var category = categoryService.getCategoryByValue(p.getCategory());
        var product = Product.builder()
                .title(p.getTitle())
                .description(p.getDescription())
                .category(category)
                .price(p.getPrice())
                .storage(p.getStorage())
                .img1(p.getImg1())
                .img2(p.getImg2())
                .sale(p.getSale())
                .build();
        productRepository.saveAndFlush(product);
        return product;
    }

    public Product update(Long id, ProductDto p) {
        var category = categoryService.getCategoryByValue(p.getCategory());
        Product product = productRepository.findById(id).orElseThrow();
        product.setId(id);
        product.setTitle(p.getTitle());
        product.setDescription(p.getDescription());
        product.setCategory(category);
        product.setPrice(p.getPrice());
        product.setStorage(p.getStorage());
        product.setImg1(p.getImg1());
        product.setImg2(p.getImg2());
        product.setSale(p.getSale());
        productRepository.saveAndFlush(product);
        return product;
    }

    public boolean delete(Long id) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteMultiple(List<Long> ids, UserDto user) {
        for (Long id : ids) {
            var product = productRepository.findById(id).orElse(null);
            if (product != null) {
                logService.insertLog(LogReqDTO.builder().user(UserDto.builder().email(user.getEmail()).fullname(user.getFullname()).phoneNumber(user.getPhoneNumber()).role(user.getRole()).build()).content("delete product with id:  " + product.getId()).build());
                productRepository.deleteById(id);
            }
        }
    }
}
