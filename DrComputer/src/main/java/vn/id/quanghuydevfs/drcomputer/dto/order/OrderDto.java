package vn.id.quanghuydevfs.drcomputer.dto.order;

import lombok.Data;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderDetail;

import java.util.List;
@Data
public class OrderDto {
    private String Customer;
    private String street;
    private String province;
    private String district;
    private String ward;
    private List<OrderDetail> orderDetails;
}
