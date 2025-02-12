package programacao.web.service;

import org.springframework.stereotype.Service;
import programacao.web.model.Usuario;
import programacao.web.repository.UsuarioRepository;
import programacao.web.dto.UsuarioDTO;
import programacao.web.exception.UsuarioException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registerUser(UsuarioDTO usuarioDTO) throws UsuarioException {
        validateNewUser(usuarioDTO);
        Usuario usuario = convertToEntity(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    public void updateUser(UsuarioDTO usuarioDTO) throws UsuarioException {
        Usuario existingUser = usuarioRepository.findByLogin(usuarioDTO.getLogin());
        if (existingUser == null) {
            throw new UsuarioException("User not found");
        }

        validateExistingUser(usuarioDTO);
        updateUserData(existingUser, usuarioDTO);
        usuarioRepository.save(existingUser);
    }

    public void deleteUser(String login) throws UsuarioException {
        Usuario existingUser = usuarioRepository.findByLogin(login);
        if (existingUser == null) {
            throw new UsuarioException("User not found");
        }
        usuarioRepository.delete(existingUser);
    }

    public List<Usuario> findAllUsers() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    private void validateNewUser(UsuarioDTO usuario) throws UsuarioException {
        StringBuilder errors = new StringBuilder();

        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            errors.append("Invalid user. There is already a user with this login registered. ");
        }

        validateCommonFields(usuario, errors);

        if (errors.length() > 0) {
            throw new UsuarioException(errors.toString().trim());
        }
    }

    private void validateExistingUser(UsuarioDTO usuario) throws UsuarioException {
        StringBuilder errors = new StringBuilder();
        validateCommonFields(usuario, errors);

        if (errors.length() > 0) {
            throw new UsuarioException(errors.toString().trim());
        }
    }

    private void validateCommonFields(UsuarioDTO usuario, StringBuilder errors) {
        if (!usuario.getEmail().equals(usuario.getEmailconfirma())) {
            errors.append("E-mails don't match. ");
        }

        if (usuario.getSenha().length() < 4 || usuario.getSenha().length() > 8) {
            errors.append("Your password should have the minimum length of four characters and the maximum of eight. ");
        }

        if (usuario.getSenha().equals(usuario.getLogin())) {
            errors.append("The password can't be the same as the login. ");
        }

        if (!usuario.getSenha().equals(usuario.getSenhaconfirma())) {
            errors.append("Passwords don't match. ");
        }
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    private void updateUserData(Usuario existingUser, UsuarioDTO dto) {
        existingUser.setNome(dto.getNome());
        existingUser.setEmail(dto.getEmail());
        existingUser.setSenha(dto.getSenha());
    }
}