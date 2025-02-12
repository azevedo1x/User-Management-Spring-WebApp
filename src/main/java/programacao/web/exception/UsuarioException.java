package programacao.web.exception;

public class UsuarioException extends RuntimeException {
    public UsuarioException() {
        super();
    }

    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}
