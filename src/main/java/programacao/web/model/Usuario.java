package programacao.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "Login is required")
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 8, message = "Password must be between 4 and 8 characters")
    @Column(nullable = false)
    private String senha;

    @Transient
    private String senhaconfirma;

    @Transient
    private String emailconfirma;

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login != null ? login.trim() : null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.trim() : null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaconfirma() {
        return senhaconfirma;
    }

    public void setSenhaconfirma(String senhaconfirma) {
        this.senhaconfirma = senhaconfirma;
    }

    public String getEmailconfirma() {
        return emailconfirma;
    }

    public void setEmailconfirma(String emailconfirma) {
        this.emailconfirma = emailconfirma != null ? emailconfirma.trim().toLowerCase() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}