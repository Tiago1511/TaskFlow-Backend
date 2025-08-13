package core.role.ports;

import core.role.domain.Role;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepositoryService {
    Optional<Role> getRole(String roleName);

    Role saveRole(Role role);
}
