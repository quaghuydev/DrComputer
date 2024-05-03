package vn.id.quanghuydevfs.drcomputer.dto.product;

import lombok.Data;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;

import java.util.List;
@Data
public class DeleteProductRequest {
    private List<Long> productIds;
    private UserDto userDto;
}
