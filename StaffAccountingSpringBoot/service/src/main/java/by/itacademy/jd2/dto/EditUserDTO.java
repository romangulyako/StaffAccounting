package by.itacademy.jd2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EditUserDTO {
    private UserDTO user;
    private List<RoleDTO> roles;
}
