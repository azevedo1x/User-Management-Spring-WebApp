package programacao.web.exception;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException(String login) {
        super("User already exists: " + login);
    }
}

