package programacao.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import programacao.web.model.User;
import programacao.web.repository.UserRepository;
import programacao.web.dto.UserDTO;
import programacao.web.exception.UserNotFoundException;
import programacao.web.exception.UserAlreadyExistsException;
import programacao.web.exception.ValidationException;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDTO userDTO) {
        logger.info("Attempting to register user: {}", userDTO.getLogin());

        if (userRepository.existsByLogin(userDTO.getLogin())) throw new UserAlreadyExistsException(userDTO.getLogin());

        validateConfirmationFields(userDTO);

        User user = convertToEntity(userDTO);
        userRepository.save(user);

        logger.info("User registered successfully: {}", userDTO.getLogin());
    }

    public void updateUser(UserDTO userDTO) {
        logger.info("Attempting to update user: {}", userDTO.getLogin());

        User existingUser = userRepository.findByLogin(userDTO.getLogin()).orElseThrow(() -> new UserNotFoundException(userDTO.getLogin()));

        validateConfirmationFields(userDTO);

        updateUserData(existingUser, userDTO);
        userRepository.save(existingUser);

        logger.info("User updated successfully: {}", userDTO.getLogin());
    }

    public void deleteUser(String login) {
        logger.info("Attempting to delete user: {}", login);

        User existingUser = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));

        userRepository.delete(existingUser);

        logger.info("User deleted successfully: {}", login);
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    private void validateConfirmationFields(UserDTO userDTO) {
        StringBuilder errors = new StringBuilder();

        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) errors.append("Passwords don't match. ");


        if (userDTO.getLogin() != null && userDTO.getPassword().equals(userDTO.getLogin()))
            errors.append("The password can't be the same as the login. ");


        if (!userDTO.getEmail().equals(userDTO.getEmailConfirmation())) errors.append("E-mails don't match. ");


        if (!errors.isEmpty()) throw new ValidationException(errors.toString().trim());

    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();

        user.setLogin(normalizeString(dto.getLogin()));
        user.setName(normalizeString(dto.getName()));
        user.setEmail(normalizeEmail(dto.getEmail()));
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return user;
    }

    private void updateUserData(User existingUser, UserDTO dto) {
        existingUser.setName(normalizeString(dto.getName()));
        existingUser.setEmail(normalizeEmail(dto.getEmail()));
        existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    private String normalizeString(String value) {
        return value != null ? value.trim() : null;
    }

    private String normalizeEmail(String email) {
        return email != null ? email.trim().toLowerCase() : null;
    }
}