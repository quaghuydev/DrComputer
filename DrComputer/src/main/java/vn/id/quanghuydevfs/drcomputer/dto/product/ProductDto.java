package vn.id.quanghuydevfs.drcomputer.dto.product;

import lombok.Data;

@Data
public class ProductDto {
    private String title;
    private String description;
    private String category;
    private int price;
    private double sale;
    private int storage;
    private String img1;
    private String img2;

}
