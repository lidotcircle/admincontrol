package hello.admincontrol.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import hello.admincontrol.entity.Role;
import hello.admincontrol.entity.PermEntry;

public interface PermEntryRepository extends CrudRepository<PermEntry, Long> {
    Optional<PermEntry> findByDescriptor(String descriptor);
    Optional<PermEntry> findByLinkAndMethod(String link, String method);

    Optional<PermEntry> findByDescriptorAndRoles_roleName(String descriptor, String roleName);
    Collection<PermEntry> findByRoles(Role role);

    Collection<PermEntry> findByParentIsNull();
}

