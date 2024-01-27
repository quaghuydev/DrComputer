package vn.id.quanghuydevfs.drcomputer.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.id.quanghuydevfs.drcomputer.model.user.Roles;

import javax.management.relation.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String fullname;
    private String email;
    private String password;
    private String phoneNumber;
    private Roles role;
}
