package programacao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import programacao.web.service.UserService;
import programacao.web.dto.UserDTO;
import programacao.web.exception.UserException;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService usuarioService)
    {
        this.userService = usuarioService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes,
        Model model){
        try {
            userService.registerUser(userDTO);
            redirectAttributes.addFlashAttribute("success", "User registered " +
            "successfully");

            return "redirect:/register";

        } catch (UserException e)
        {
            e.printStackTrace();
            model.addAttribute("user", userDTO);
            model.addAttribute("error", e.getMessage());

            return "recordView";
        }
    }

    @GetMapping("/update")
    public String showEditForm(Model model)
    {
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes,
        Model model) {
        try {
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("success", "User updated "
            + "successfully");

            return "redirect:/update";

        } catch (UserException e)
        {
            model.addAttribute("error", e.getMessage());
            return "recordView";
        }
    }

    @GetMapping("/delete")
    public String showRemovalForm(Model model)
    {
        model.addAttribute("user", new UserDTO());
        return "deleteView";
    }

    @PostMapping("/delete")
    public String removeUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes,
            Model model) {
        try {
            userService.deleteUser(userDTO.getLogin());
            redirectAttributes.addFlashAttribute("success", "User removed "
            + "successfully");
            
            return "redirect:/remover";

        } catch (UserException e)
        {
            model.addAttribute("error", e.getMessage());
            return "deleteView";
        }
    }

    @GetMapping("/read")
    public String listUsers(Model model)
    {
        model.addAttribute("users", userService.findAllUsers());
        return "readView";
    }
}