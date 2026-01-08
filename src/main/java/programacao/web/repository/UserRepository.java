package programacao.web.repository;

import org.springframework.data.repository.CrudRepository;
import programacao.web.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}