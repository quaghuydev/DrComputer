package vn.id.quanghuydevfs.drcomputer.controller.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.order.Order;
import vn.id.quanghuydevfs.drcomputer.model.order.OrderDetail;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    @JsonProperty("order")
    private Order order;
    @JsonProperty("details")
    private List<OrderDetail> orderDetails;
    @JsonProperty("user")
    private UserDto user;
}
