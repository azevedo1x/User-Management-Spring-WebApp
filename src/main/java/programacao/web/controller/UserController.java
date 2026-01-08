package programacao.web.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import programacao.web.service.UserService;
import programacao.web.dto.UserDTO;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (bindingResult.hasErrors())
            return "recordView";


        userService.registerUser(userDTO);
        redirectAttributes.addFlashAttribute("success", "User registered successfully");
        return "redirect:/register";
    }

    @GetMapping("/update")
    public String showEditForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @PostMapping("/update")
    public String editUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "recordView";
        }

        userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("success", "User updated successfully");
        return "redirect:/update";
    }

    @GetMapping("/delete")
    public String showRemovalForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "deleteView";
    }

    @PostMapping("/delete")
    public String removeUser(@ModelAttribute UserDTO userDTO,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        userService.deleteUser(userDTO.getLogin());
        redirectAttributes.addFlashAttribute("success", "User removed successfully");
        return "redirect:/delete";
    }

    @GetMapping("/read")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "readView";
    }
}