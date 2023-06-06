package programacao.web.repository;

import org.springframework.data.repository.CrudRepository;
import programacao.web.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findById(long id);

}