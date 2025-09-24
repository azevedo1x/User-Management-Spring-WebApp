package programacao.web.service;

import org.springframework.stereotype.Service;
import programacao.web.model.User;
import programacao.web.repository.UserRepository;
import programacao.web.dto.UserDTO;
import programacao.web.exception.UserException;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) throws UserException
    {
        validateNewUser(userDTO);

        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO) throws UserException
    {
        User existingUser = userRepository.findByLogin(userDTO.getLogin());

        if (existingUser == null)
            throw new UserException("User not found");

        validateExistingUser(userDTO);
        updateUserData(existingUser, userDTO);

        userRepository.save(existingUser);
    }

    public void deleteUser(String login) throws UserException
    {
        User existingUser = userRepository.findByLogin(login);

        if (existingUser == null)
            throw new UserException("User not found");

        userRepository.delete(existingUser);
    }

    public List<User> findAllUsers()
    {
        return (List<User>) userRepository.findAll();
    }

    private void validateNewUser(UserDTO user) throws UserException
    {
        StringBuilder errors = new StringBuilder();

        if (userRepository.findByLogin(user.getLogin()) != null)
            errors.append("Invalid user. There is already a user with this login registered. ");

        validateCommonFields(user, errors);

        if (!errors.isEmpty())
            throw new UserException(errors.toString().trim());
    }

    private void validateExistingUser(UserDTO user) throws UserException
    {
        StringBuilder errors = new StringBuilder();
        validateCommonFields(user, errors);

        if (!errors.isEmpty())
            throw new UserException(errors.toString().trim());
    }

    private void validateCommonFields(UserDTO user, StringBuilder errors)
    {
        validateEmptyFields(user, errors);
        validatePassword(user, errors);
        validateEmail(user, errors);
    }
    
    private void validateEmptyFields(UserDTO user, StringBuilder errors)
    {
        Map<String, String> inputs = Map.of(
            "Login", user.getLogin(),
            "Password", user.getPassword(),
            "Password Confirmation", user.getPasswordConfirmation(),
            "Name", user.getName(),
            "Email", user.getEmail(),
            "Email Confirmation", user.getEmailConfirmation()
        );
    
        inputs.forEach((key, value) -> {
            if (value == null || value.isBlank()) {
                errors.append(key).append(" is empty. ");
            }
        });
    }
    
    private void validatePassword(UserDTO user, StringBuilder errors)
    {
        String password = user.getPassword();
    
        if (password.length() < 4 || password.length() > 8)
            errors.append("Your password should have between 4 and 8 characters. ");
    
        if (user.getLogin() != null && password.equals(user.getLogin()))
            errors.append("The password can't be the same as the login. ");
    
        if (!password.equals(user.getPasswordConfirmation()))
            errors.append("Passwords don't match. ");
    }
    
    private void validateEmail(UserDTO user, StringBuilder errors)
    {
        if (!user.getEmail().equals(user.getEmailConfirmation()))
            errors.append("E-mails don't match. ");
    }
    

    private User convertToEntity(UserDTO dto)
    {
        User user = new User();
        
        user.setLogin(dto.getLogin());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    private void updateUserData(User existingUser, UserDTO dto)
    {
        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(dto.getPassword());
    }
}