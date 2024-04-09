package vn.id.quanghuydevfs.drcomputer.controller.admin.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.controller.auth.AuthenticationResponse;
import vn.id.quanghuydevfs.drcomputer.dto.log.LogReqDTO;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.user.User;
import vn.id.quanghuydevfs.drcomputer.service.LogService;
import vn.id.quanghuydevfs.drcomputer.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/management/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;
    private final LogService logService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        var user = userService.getUserById(id);
        if (user.isPresent()) {
            logService.insertLog(LogReqDTO.builder().user(UserDto.builder().email(user.get().getEmail())
                            .phoneNumber(user.get().getPhoneNumber())
                            .fullname(user.get().getFullname())
                            .role(user.get().getRoles())
                    .build()).content("delete user "+user.get().getUsername()).build());
            return ResponseEntity.ok(userService.deleteUserById(id));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthenticationResponse> updateUser(@PathVariable Long id, @RequestBody UserDto user) {

        if (id != null) {
            return ResponseEntity.ok(userService.updateUserById(id, user));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
