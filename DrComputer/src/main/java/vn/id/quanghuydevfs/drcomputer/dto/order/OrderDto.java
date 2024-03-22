package vn.id.quanghuydevfs.drcomputer.dto.order;

import lombok.Data;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderDetail;
import vn.id.quanghuydevfs.drcomputer.model.user.User;

import java.util.List;
@Data
public class OrderDto {
    private String customer;
    private String street;
    private String province;
    private String district;
    private String ward;
    private List<OrderDetail> orderDetails;
    private UserDto user;
}
