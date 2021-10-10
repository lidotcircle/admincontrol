package hello.admincontrol.service;

import hello.admincontrol.entity.Role;
import hello.admincontrol.service.dto.role.PermEntryItemDTO;
import hello.admincontrol.service.dto.role.PermEntryTreeDTO;

import java.util.Collection;
import java.util.Optional;


public interface RoleService {
    void createRole(String roleName);
    boolean hasRole(String roleName);
    void deleteRole(String roleName);
    Optional<Role> getRoleByRoleName(String roleName);
    Collection<Role> getAllRoles();

    void updateRoleName(String oldRoleName, String newRoleName);

    boolean hasPermission(String roleName, String descriptor);
    boolean hasPermissionInLink(String roleName, String link, String method);
    void enablePermEntry(String roleName, String descriptor);
    void disablePermEntry(String roleName, String descriptor);
    void enablePermEntryRecursively(String roleName, String descriptor);
    void disablePermEntryRecursively(String roleName, String descriptor);

    void addPermEntry(String descriptor, PermEntryItemDTO perm);
    void removePermEntry(String descriptor);
    void removePermEntryRecursively(String descriptor);
    void updatePermEntry(String descriptor, PermEntryItemDTO perm);

    PermEntryItemDTO getPermEntryItemByDescriptor(String descriptor);

    Collection<PermEntryTreeDTO> getPermEntryTree();
    Collection<PermEntryItemDTO> getPermEntries(String roleName);

    Collection<Role> getRolesByPermEntry(String descriptor);
}

