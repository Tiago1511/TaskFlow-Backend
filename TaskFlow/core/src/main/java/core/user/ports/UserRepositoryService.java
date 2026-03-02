package core.user.ports;

import core.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryService {

    List<User> findByEmailOrUserName(String email, String userName);

    User saveUser(User user);

    Optional<User> findByEmail(String email);
}
