package vn.id.quanghuydevfs.drcomputer.model.product;

import jakarta.persistence.*;
import lombok.Data;
import vn.id.quanghuydevfs.drcomputer.model.enums.Category;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;

    private Category category;
    private int price;
    private int storage;

}
