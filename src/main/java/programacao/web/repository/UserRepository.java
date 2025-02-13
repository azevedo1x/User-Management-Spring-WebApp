package programacao.web.repository;

import org.springframework.data.repository.CrudRepository;
import programacao.web.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByLogin(String login);

}