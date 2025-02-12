package programacao.web.dto;

public class UsuarioDTO {
    private String login;
    private String nome;
    private String email;
    private String emailconfirma;
    private String senha;
    private String senhaconfirma;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailconfirma() {
        return emailconfirma;
    }

    public void setEmailconfirma(String emailconfirma) {
        this.emailconfirma = emailconfirma;
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
}