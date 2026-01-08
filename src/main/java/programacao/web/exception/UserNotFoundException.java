package programacao.web.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(String login) {
        super("User not found: " + login);
    }
}
