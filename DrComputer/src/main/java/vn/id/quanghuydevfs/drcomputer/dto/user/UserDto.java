package vn.id.quanghuydevfs.drcomputer.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.id.quanghuydevfs.drcomputer.model.user.Roles;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String fullname;
    private String email;
    private String phoneNumber;
    private Roles role;
}
