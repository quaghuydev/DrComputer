package vn.id.quanghuydevfs.drcomputer.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.dto.product.DeleteProductRequest;
import vn.id.quanghuydevfs.drcomputer.dto.product.ProductDto;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/product")
//@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")
public class AdminProductController {
    @Autowired
    private ProductService productService;

//    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.add(productDto));
    }
//    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

//    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
//    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/products")
    public ResponseEntity<Void> deleteProduct(@RequestBody DeleteProductRequest request) {
        productService.deleteMultiple(request.getProductIds(), request.getUserDto());
        return ResponseEntity.noContent().build();
    }

}
