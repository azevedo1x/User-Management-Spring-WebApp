package programacao.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import programacao.web.repository.UserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void home_shouldReturnIndexPage() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @Test
    void showRegistrationForm_shouldReturnRecordView() throws Exception {
        mockMvc.perform(get("/register"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    void registerUser_withValidData_shouldRedirect() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "testuser")
                .param("name", "Test User")
                .param("email", "test@example.com")
                .param("emailConfirmation", "test@example.com")
                .param("password", "SecureP@ss123")
                .param("passwordConfirmation", "SecureP@ss123"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/register"));
    }

    @Test
    void registerUser_withInvalidPassword_shouldReturnErrors() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "testuser")
                .param("name", "Test User")
                .param("email", "test@example.com")
                .param("emailConfirmation", "test@example.com")
                .param("password", "weak")
                .param("passwordConfirmation", "weak"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"));
    }

    @Test
    void registerUser_withMismatchedPasswords_shouldReturnError() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "testuser")
                .param("name", "Test User")
                .param("email", "test@example.com")
                .param("emailConfirmation", "test@example.com")
                .param("password", "SecureP@ss123")
                .param("passwordConfirmation", "DifferentP@ss123"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"))
            .andExpect(model().attributeExists("error"));
    }

    @Test
    void registerUser_withMismatchedEmails_shouldReturnError() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "testuser")
                .param("name", "Test User")
                .param("email", "test@example.com")
                .param("emailConfirmation", "different@example.com")
                .param("password", "SecureP@ss123")
                .param("passwordConfirmation", "SecureP@ss123"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"))
            .andExpect(model().attributeExists("error"));
    }

    @Test
    void registerUser_withInvalidEmail_shouldReturnErrors() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "testuser")
                .param("name", "Test User")
                .param("email", "invalid-email")
                .param("emailConfirmation", "invalid-email")
                .param("password", "SecureP@ss123")
                .param("passwordConfirmation", "SecureP@ss123"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"));
    }

    @Test
    void registerUser_withBlankFields_shouldReturnErrors() throws Exception {
        mockMvc.perform(post("/register")
                .param("login", "")
                .param("name", "")
                .param("email", "")
                .param("emailConfirmation", "")
                .param("password", "")
                .param("passwordConfirmation", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"));
    }

    @Test
    void showEditForm_shouldReturnRecordView() throws Exception {
        mockMvc.perform(get("/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recordView"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    void showRemovalForm_shouldReturnDeleteView() throws Exception {
        mockMvc.perform(get("/delete"))
            .andExpect(status().isOk())
            .andExpect(view().name("deleteView"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    void listUsers_shouldReturnReadView() throws Exception {
        mockMvc.perform(get("/read"))
            .andExpect(status().isOk())
            .andExpect(view().name("readView"))
            .andExpect(model().attributeExists("users"));
    }
}

