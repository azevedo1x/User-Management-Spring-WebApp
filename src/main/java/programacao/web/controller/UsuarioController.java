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

    private Iterable<Usuario> usuarios;

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
    public String exibirForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
    public String processarForm(Usuario usuario) {

        ur.save(usuario);
        verificarUsuario();
        return "redirect:/cadastrarUsuario";

    }

    public Iterable<Usuario> preencherIterable() {

        Iterable<Usuario> usuarios = ur.findAll();

        return usuarios;

    }

    @RequestMapping("/listarUsuario")
    public String mostrarUsuario(Model model) {

        preencherIterable();

        model.addAttribute("usuarios", usuarios);

        return "listarUsuario";

    }

    public void verificarUsuario() {

        String login_temp = null;

        for (Usuario usuario : usuarios) {

            try {

                if (login_temp == usuario.getLogin()) {

                    throw new Excecao("Usuário inválido.");

                }

                if (usuario.getEmail() != usuario.getEmailconfirma()) {

                    throw new Excecao("E-mails não coincidem.");

                }

            } catch (Excecao e) {

                ur.delete(usuario);

            }

            login_temp = usuario.getLogin();

        }
    }

}