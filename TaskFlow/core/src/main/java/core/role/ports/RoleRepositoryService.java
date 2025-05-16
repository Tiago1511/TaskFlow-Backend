package core.role.ports;

import core.role.domain.Role;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepositoryService {
    Collection<Role> getRole(String roleName);

    Role saveRole(Role role);
}
