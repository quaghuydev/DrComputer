package vn.id.quanghuydevfs.drcomputer.dto.log;

import lombok.Builder;
import lombok.Data;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;

@Data
@Builder
public class LogReqDTO {
    private UserDto user;
    private String content;
}
