package programacao.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import programacao.web.dto.UserDTO;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model) {
        logger.error("User not found: {}", ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserDTO());
        return "deleteView";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {
        logger.warn("Duplicate user creation attempt: {}", ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        logger.warn("Validation error: {}", ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Model model) {
        BindingResult bindingResult = ex.getBindingResult();
        String errors = bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        logger.warn("Validation failed: {}", errors);
        model.addAttribute("error", errors);
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        logger.error("Unexpected error", ex);
        model.addAttribute("error", "An unexpected error occurred. Please try again.");
        model.addAttribute("user", new UserDTO());
        return "recordView";
    }
}

