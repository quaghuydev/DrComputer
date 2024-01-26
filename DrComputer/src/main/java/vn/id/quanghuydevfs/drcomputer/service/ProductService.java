package vn.id.quanghuydevfs.drcomputer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.repository.ProductRepsitory;

@Service
public class ProductService {
    private final ProductRepsitory productRepsitory;

    @Autowired
    public ProductService(ProductRepsitory productRepsitory) {
        this.productRepsitory = productRepsitory;
    }
}
