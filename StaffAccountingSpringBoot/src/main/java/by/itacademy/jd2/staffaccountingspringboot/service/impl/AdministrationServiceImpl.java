package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.dto.EditUserDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.RoleDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.UserDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.RoleEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.UserEntity;
import by.itacademy.jd2.staffaccountingspringboot.repository.RoleRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.UserRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.AdministrationService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministrationServiceImpl implements AdministrationService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        UserEntity user = Converter.toEntity(userDTO, UserEntity.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        for (String authority : userDTO.getAuthorities()) {
            RoleEntity role = roleRepository.findByAuthority(authority)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.getAuthorities().add(role);
        }
        return Converter.toDto(userRepository.save(user), UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public EditUserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UserDTO userDTO = Converter.toDto(user, UserDTO.class);
        userDTO.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<RoleDTO> roles = this.getRoles();
        return EditUserDTO.builder()
                .user(userDTO)
                .roles(roles)
                .build();
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .map(entity -> Converter.toDto(entity, UserDTO.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(LocaleUtils
                        .getMessage(Constant.USER_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + username));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return this.getRoles();
    }

    private List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream().map(entity -> Converter.toDto(entity, RoleDTO.class)).toList();
    }
}
