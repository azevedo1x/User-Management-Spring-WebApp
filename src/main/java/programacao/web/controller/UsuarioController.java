package programacao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {
    @RequestMapping(value = "/cadastrarUsuario")
    public String form() {
        return "formUsuario";
    }
}