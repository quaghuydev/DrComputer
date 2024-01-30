package vn.id.quanghuydevfs.drcomputer.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.exception.NotEnoughProductsInStockException;
import vn.id.quanghuydevfs.drcomputer.model.cart.Cart;
import vn.id.quanghuydevfs.drcomputer.model.product.Product;
import vn.id.quanghuydevfs.drcomputer.service.CartService;
import vn.id.quanghuydevfs.drcomputer.service.ProductService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {
    @Autowired
    private final CartService cartService;
    @Autowired
    private final ProductService productService;

    @GetMapping("/cart")
    public ResponseEntity<Map<Product, Integer>> shoppingCart() {

        return ResponseEntity.ok(cartService.getProductsInCart());
    }

    @GetMapping("/cart/add/{id}")
    public ResponseEntity<Map<Product, Integer>> addCart(@PathVariable Long id) {
        productService.getProductById(id).ifPresent(cartService::addProduct);
        return shoppingCart();
    }

    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity<Map<Product, Integer>> deleteCart(@PathVariable Long id) {
        productService.getProductById(id).ifPresent(cartService::removeProduct);
        return shoppingCart();
    }

    @DeleteMapping("/cart/checkout/")
    public ResponseEntity<Map<Product, Integer>> checkout(@PathVariable Long id) throws NotEnoughProductsInStockException {
        cartService.checkout();
        return shoppingCart();
    }
}
