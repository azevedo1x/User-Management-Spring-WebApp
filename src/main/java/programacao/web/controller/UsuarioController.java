package programacao.web.controller;

import org.springframework.stereotype.Controller;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import programacao.web.model.Usuario;
import programacao.web.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    private UsuarioRepository ur;
    
    // @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.ur = usuarioRepository;
    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
    public String exibirForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
    public String processarForm(Usuario usuario) {

        ur.save(usuario);
        return "redirect:/cadastrarUsuario";

    }

    @RequestMapping("/listarUsuario")
    public ModelAndView listarUsuario() {

        ModelAndView mv = new ModelAndView("listarUsuario");

        Iterable<Usuario> usuarios = ur.findAll();

        mv.addObject("usuarios", usuarios);

        return mv;

    }
}