package hello.admincontrol.repository;

import org.springframework.data.repository.CrudRepository;
import hello.admincontrol.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role getRoleByRoleName(String roleName);
}

