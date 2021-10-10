package hello.admincontrol.service.dto.role;

import javax.validation.constraints.NotNull;


public class RolePermEntryItemPostDTO {
    @NotNull
    private String descriptor;
    public String getDescriptor() {
        return this.descriptor;
    }
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @NotNull
    private String roleName;
    public String getRoleName() {
        return this.roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private boolean recursive;
    public boolean getRecursive() {
        return this.recursive;
    }
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }
}

