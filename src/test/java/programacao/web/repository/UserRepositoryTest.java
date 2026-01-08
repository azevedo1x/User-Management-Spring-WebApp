package programacao.web.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import programacao.web.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        testUser = new User();
        testUser.setLogin("testuser");
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
    }

    @Test
    void save_shouldPersistUser() {
        // When
        User savedUser = userRepository.save(testUser);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getLogin()).isEqualTo("testuser");
        assertThat(savedUser.getCreatedAt()).isNotNull();
    }

    @Test
    void findByLogin_whenUserExists_shouldReturnUser() {
        // Given
        userRepository.save(testUser);

        // When
        Optional<User> found = userRepository.findByLogin("testuser");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getLogin()).isEqualTo("testuser");
        assertThat(found.get().getName()).isEqualTo("Test User");
    }

    @Test
    void findByLogin_whenUserDoesNotExist_shouldReturnEmpty() {
        // When
        Optional<User> found = userRepository.findByLogin("nonexistent");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    void existsByLogin_whenUserExists_shouldReturnTrue() {
        // Given
        userRepository.save(testUser);

        // When
        boolean exists = userRepository.existsByLogin("testuser");

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByLogin_whenUserDoesNotExist_shouldReturnFalse() {
        // When
        boolean exists = userRepository.existsByLogin("nonexistent");

        // Then
        assertThat(exists).isFalse();
    }

    @Test
    void findByEmail_whenUserExists_shouldReturnUser() {
        // Given
        userRepository.save(testUser);

        // When
        Optional<User> found = userRepository.findByEmail("test@example.com");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void existsByEmail_whenEmailExists_shouldReturnTrue() {
        // Given
        userRepository.save(testUser);

        // When
        boolean exists = userRepository.existsByEmail("test@example.com");

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void delete_shouldRemoveUser() {
        // Given
        User savedUser = userRepository.save(testUser);

        // When
        userRepository.delete(savedUser);

        // Then
        Optional<User> found = userRepository.findByLogin("testuser");
        assertThat(found).isEmpty();
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        // Given
        User user1 = new User();
        user1.setLogin("user1");
        user1.setName("User One");
        user1.setEmail("user1@example.com");
        user1.setPassword("pass1");

        User user2 = new User();
        user2.setLogin("user2");
        user2.setName("User Two");
        user2.setEmail("user2@example.com");
        user2.setPassword("pass2");

        userRepository.save(user1);
        userRepository.save(user2);

        // When
        List<User> users = (List<User>) userRepository.findAll();

        // Then
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getLogin).containsExactlyInAnyOrder("user1", "user2");
    }

    @Test
    void update_shouldModifyExistingUser() {
        // Given
        User savedUser = userRepository.save(testUser);

        // When
        savedUser.setName("Updated Name");
        savedUser.setEmail("updated@example.com");
        User updatedUser = userRepository.save(savedUser);

        // Then
        assertThat(updatedUser.getName()).isEqualTo("Updated Name");
        assertThat(updatedUser.getEmail()).isEqualTo("updated@example.com");
        assertThat(updatedUser.getUpdatedAt()).isNotNull();
    }
}

