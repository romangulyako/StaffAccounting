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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(AdministrationServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_USER, userDTO.getUsername());
        UserEntity user = Converter.toEntity(userDTO, UserEntity.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        for (String authority : userDTO.getAuthorities()) {
            RoleEntity role = roleRepository.findByAuthority(authority)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.getAuthorities().add(role);
        }
        UserEntity savedUser = userRepository.save(user);
        LOGGER.info(Constant.SUCCESSFULLY_SAVED_USER, userDTO.getId());

        return Converter.toDto(savedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_USER, id);
        userRepository.deleteById(id);
        LOGGER.info(Constant.SUCCESSFULLY_DELETED_USER, id);
    }

    @Override
    @Transactional(readOnly = true)
    public EditUserDTO getUserById(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_USER, id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LocaleUtils
                        .getMessage(Constant.USER_BY_ID_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id));
        UserDTO userDTO = Converter.toDto(user, UserDTO.class);
        List<RoleDTO> roles = this.getRoles();
        LOGGER.info(Constant.SUCCESSFULLY_FETCHED_USER, id);
        return EditUserDTO.builder()
                .user(userDTO)
                .roles(roles)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_PAGE_WITH_USERS);
        Page<UserDTO> usersPage = userRepository.findAll(PageRequest.of(page, size))
                .map(entity -> Converter.toDto(entity, UserDTO.class));
        if (usersPage.hasContent()) {
            LOGGER.info(Constant.SUCCESSFULLY_FETCHED_PAGE_WITH_USERS, page, size);
        } else {
            LOGGER.warn(Constant.NO_FOUND_USERS, page, size);
        }
        
        return usersPage;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(LocaleUtils
                        .getMessage(Constant.USER_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + username));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        return this.getRoles();
    }

    private List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream().map(entity -> Converter.toDto(entity, RoleDTO.class)).toList();
    }
}
