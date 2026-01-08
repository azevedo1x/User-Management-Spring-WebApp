package programacao.web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import programacao.web.dto.UserDTO;
import programacao.web.exception.UserAlreadyExistsException;
import programacao.web.exception.UserNotFoundException;
import programacao.web.exception.ValidationException;
import programacao.web.model.User;
import programacao.web.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserDTO testUserDTO;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUserDTO = new UserDTO();
        testUserDTO.setLogin("testuser");
        testUserDTO.setName("Test User");
        testUserDTO.setEmail("test@example.com");
        testUserDTO.setEmailConfirmation("test@example.com");
        testUserDTO.setPassword("SecurePass123!");
        testUserDTO.setPasswordConfirmation("SecurePass123!");

        testUser = new User();
        testUser.setLogin("testuser");
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
    }

    @Test
    void registerUser_whenUserDoesNotExist_shouldSaveUser() {
        // Given
        when(userRepository.existsByLogin(testUserDTO.getLogin())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // When
        userService.registerUser(testUserDTO);

        // Then
        verify(userRepository).existsByLogin(testUserDTO.getLogin());
        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode(testUserDTO.getPassword());
    }

    @Test
    void registerUser_whenUserExists_shouldThrowUserAlreadyExistsException() {
        // Given
        when(userRepository.existsByLogin(testUserDTO.getLogin())).thenReturn(true);

        // When & Then
        assertThrows(UserAlreadyExistsException.class,
            () -> userService.registerUser(testUserDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_whenPasswordsDontMatch_shouldThrowValidationException() {
        // Given
        testUserDTO.setPasswordConfirmation("DifferentPassword123!");
        when(userRepository.existsByLogin(testUserDTO.getLogin())).thenReturn(false);

        // When & Then
        assertThrows(ValidationException.class,
            () -> userService.registerUser(testUserDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_whenEmailsDontMatch_shouldThrowValidationException() {
        // Given
        testUserDTO.setEmailConfirmation("different@example.com");
        when(userRepository.existsByLogin(testUserDTO.getLogin())).thenReturn(false);

        // When & Then
        assertThrows(ValidationException.class,
            () -> userService.registerUser(testUserDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_whenPasswordEqualsLogin_shouldThrowValidationException() {
        // Given
        testUserDTO.setPassword("testuser");
        testUserDTO.setPasswordConfirmation("testuser");
        when(userRepository.existsByLogin(testUserDTO.getLogin())).thenReturn(false);

        // When & Then
        assertThrows(ValidationException.class,
            () -> userService.registerUser(testUserDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUser_whenUserExists_shouldUpdateUser() {
        // Given
        when(userRepository.findByLogin(testUserDTO.getLogin())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");

        // When
        userService.updateUser(testUserDTO);

        // Then
        verify(userRepository).findByLogin(testUserDTO.getLogin());
        verify(userRepository).save(testUser);
        verify(passwordEncoder).encode(testUserDTO.getPassword());
    }

    @Test
    void updateUser_whenUserDoesNotExist_shouldThrowUserNotFoundException() {
        // Given
        when(userRepository.findByLogin(testUserDTO.getLogin())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class,
            () -> userService.updateUser(testUserDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_whenUserExists_shouldDeleteUser() {
        // Given
        when(userRepository.findByLogin("testuser")).thenReturn(Optional.of(testUser));

        // When
        userService.deleteUser("testuser");

        // Then
        verify(userRepository).findByLogin("testuser");
        verify(userRepository).delete(testUser);
    }

    @Test
    void deleteUser_whenUserDoesNotExist_shouldThrowUserNotFoundException() {
        // Given
        when(userRepository.findByLogin("nonexistent")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class,
            () -> userService.deleteUser("nonexistent"));
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    void findAllUsers_shouldReturnAllUsers() {
        // Given
        User user1 = new User();
        user1.setLogin("user1");
        User user2 = new User();
        user2.setLogin("user2");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.findAllUsers();

        // Then
        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }
}

