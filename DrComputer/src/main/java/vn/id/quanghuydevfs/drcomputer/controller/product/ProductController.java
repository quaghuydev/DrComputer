package vn.id.quanghuydevfs.drcomputer.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "all") String category, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String sort) {
        return ResponseEntity.ok(productService.getProducts(page, size, sort, category, search));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> search(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getProductByName(keyword, page, size));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

//    @Secured("ADMIN")
//    @PostMapping("/management/product/add")
//    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
//        return ResponseEntity.ok(productService.add(productDto));
//    }
//
//    @Secured("/ADMIN")
//
//    @PutMapping("/management/product/update/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
//        return ResponseEntity.ok(productService.update(id, productDto));
//    }
//
//    @Secured("ADMIN")
//
//    @DeleteMapping("/management/product/delete/{id}")
//    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.delete(id));
//    }
}
