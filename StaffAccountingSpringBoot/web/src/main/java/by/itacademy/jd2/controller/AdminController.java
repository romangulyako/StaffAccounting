package by.itacademy.jd2.controller;

import by.itacademy.jd2.dto.EditUserDTO;
import by.itacademy.jd2.dto.PageFilter;
import by.itacademy.jd2.dto.UserDTO;
import by.itacademy.jd2.service.api.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final AdministrationService adminService;

    @GetMapping("/create")
    public String createUserPage(Model model) {
        model.addAttribute("roles", adminService.getAllRoles());
        model.addAttribute("user", new UserDTO());
        return "users/create-user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") UserDTO userDTO) {
        adminService.saveOrUpdateUser(userDTO);
        return "redirect:/users";
    }

    @GetMapping
    public String getUsers(PageFilter pageFilter,
                           Model model) {
        Page<UserDTO> usersPage = adminService.getAllUsers(pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "users/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        EditUserDTO editUserDTO = adminService.getUserById(id);
        model.addAttribute("user", editUserDTO.getUser());
        model.addAttribute("roles", editUserDTO.getRoles());
        return "users/edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") UserDTO userDTO) {
        adminService.saveOrUpdateUser(userDTO);
        return "redirect:/users";
    }
}
