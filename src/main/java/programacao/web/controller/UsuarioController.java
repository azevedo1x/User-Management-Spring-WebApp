package programacao.web.controller;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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
    public String processarForm(Usuario usuario, Model model) {

        try {

            verificarUsuario(usuario);

        } catch (Exception e) {

            model.addAttribute("erro", e.getMessage());
            return "formUsuario";

        }

        ur.save(usuario);
        return "redirect:/cadastrarUsuario";

    }

    @RequestMapping("/listarUsuario")
    public String mostrarUsuario(Model model) {

        Iterable<Usuario> usuarios = ur.findAll();
        model.addAttribute("usuarios", usuarios);

        return "listarUsuario";

    }

    public void verificarUsuario(Usuario usuario) throws Excecao {

        List<String> mensagensExcecao = new ArrayList<>();
        Usuario usuarioExistente = ur.findByLogin(usuario.getLogin());

        if (usuarioExistente != null) {

            mensagensExcecao.add("Usuário inválido. Já existe um usuário com o mesmo login.");

        }

        if (!usuario.getEmail().equals(usuario.getEmailconfirma())) {

            mensagensExcecao.add("E-mails não coincidem.");

        }

        if (usuario.getSenha().length() < 4 || usuario.getSenha().length() > 8) {

            mensagensExcecao.add("Sua senha deve ter um mínimo de 4 caracteres e um máximo de 8 caracteres.");

        }

        if (usuario.getSenha().equals(usuario.getLogin())) {

            mensagensExcecao.add("A senha não pode ser igual ao login.");

        }

        if (!usuario.getSenha().equals(usuario.getSenhaconfirma())) {

            mensagensExcecao.add("Senhas não coincidem.");

        }

        if (!mensagensExcecao.isEmpty()) {

            throw new Excecao(String.join(" ", mensagensExcecao));

        }
    }

}
