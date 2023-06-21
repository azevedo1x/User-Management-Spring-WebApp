package programacao.web.controller;

public class Excecao extends Exception {
    public Excecao() {
        super();
    }

    public Excecao(String mensagem) {
        super(mensagem);
    }
}
