package vn.id.quanghuydevfs.drcomputer.model.product;

import jakarta.persistence.*;
import vn.id.quanghuydevfs.drcomputer.model.product.Category;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;
    private int price;
    private int storage;

}
