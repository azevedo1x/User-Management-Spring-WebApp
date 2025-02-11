package programacao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import programacao.web.model.Usuario;
import programacao.web.service.UsuarioService;
import programacao.web.dto.UsuarioDTO;
import programacao.web.exception.UsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastrar")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "formUsuario";
    }

    @PostMapping("/cadastrar")
    public String registerUser(@ModelAttribute UsuarioDTO usuarioDTO, 
                             RedirectAttributes redirectAttributes, 
                             Model model) {
        try {
            usuarioService.registerUser(usuarioDTO);
            redirectAttributes.addFlashAttribute("success", "User registered successfully");
            return "redirect:/usuarios/cadastrar";
        } catch (UsuarioException e) {
            model.addAttribute("erro", e.getMessage());
            return "formUsuario";
        }
    }

    @GetMapping("/editar")
    public String showEditForm(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "formUsuario";
    }

    @PostMapping("/editar")
    public String editUser(@ModelAttribute UsuarioDTO usuarioDTO, 
                          RedirectAttributes redirectAttributes, 
                          Model model) {
        try {
            usuarioService.updateUser(usuarioDTO);
            redirectAttributes.addFlashAttribute("success", "User updated successfully");
            return "redirect:/usuarios/editar";
        } catch (UsuarioException e) {
            model.addAttribute("erro", e.getMessage());
            return "formUsuario";
        }
    }

    @GetMapping("/remover")
    public String showRemovalForm(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "formRemocao";
    }

    @PostMapping("/remover")
    public String removeUser(@ModelAttribute UsuarioDTO usuarioDTO, 
                           RedirectAttributes redirectAttributes, 
                           Model model) {
        try {
            usuarioService.deleteUser(usuarioDTO.getLogin());
            redirectAttributes.addFlashAttribute("success", "User removed successfully");
            return "redirect:/usuarios/remover";
        } catch (UsuarioException e) {
            model.addAttribute("erro", e.getMessage());
            return "formRemocao";
        }
    }

    @GetMapping("/listar")
    public String listUsers(Model model) {
        model.addAttribute("usuarios", usuarioService.findAllUsers());
        return "listarUsuario";
    }
}