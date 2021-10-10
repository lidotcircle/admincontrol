package hello.admincontrol.service.dto.role;

import java.util.Collection;


public class PermEntryTreeDTO extends PermEntryItemDTO
{
    private Collection<PermEntryTreeDTO> children;
    public Collection<PermEntryTreeDTO> getChildren() {
        return this.children;
    }
    public void setChildren(Collection<PermEntryTreeDTO> children) {
        this.children = children;
    }
}

