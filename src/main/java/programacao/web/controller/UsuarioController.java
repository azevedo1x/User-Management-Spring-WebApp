package programacao.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import programacao.web.model.Usuario;
import programacao.web.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    private UsuarioRepository ur;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.ur = usuarioRepository;
    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo objeto Usuario ao modelo
        return "formUsuario";
    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
    public String form(Usuario usuario) {

        ur.save(usuario);
        return "redirect:/cadastrarUsuario";

    }
}