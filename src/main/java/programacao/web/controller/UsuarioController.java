package programacao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import programacao.web.model.Usuario;
import programacao.web.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository ur;

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
    public String exibirForm(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "formUsuario";

    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
    public String processarForm(Usuario usuario){

        verificarUsuario(usuario);

        ur.save(usuario);
        return "redirect:/cadastrarUsuario";

    }

    @RequestMapping("/listarUsuario")
    public String mostrarUsuario(Model model) {

        Iterable<Usuario> usuarios = ur.findAll();
        model.addAttribute("usuarios", usuarios);

        return "listarUsuario";

    }

    public void verificarUsuario(Usuario usuario){

        Usuario existingUser = ur.findByLogin(usuario.getLogin());

        if (existingUser != null) {

            throw new Excecao("Usuário inválido. Já existe um usuário com o mesmo login.");

        }

        if (!usuario.getEmail().equals(usuario.getEmailconfirma())) {

            throw new Excecao("E-mails não coincidem.");

        }
    }
}
