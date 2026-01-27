package core.user.ports;

import core.user.domain.User;

import java.util.List;

public interface UserRepositoryService {

    List<User> findByEmailOrUserName(String email, String userName);

    User saveUser(User user);
}
