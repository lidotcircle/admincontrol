package hello.admincontrol.controller.role;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import hello.admincontrol.service.RoleService;
import hello.admincontrol.service.dto.role.PermEntryItemDTO;
import hello.admincontrol.service.dto.role.PermEntryItemPostDTO;
import hello.admincontrol.service.dto.role.PermEntryItemPutDTO;
import hello.admincontrol.service.dto.role.PermEntryTreeDTO;
import hello.admincontrol.utils.MyBeanUtils;


@Tag(name = "权限菜单")
@RestController
@RequestMapping("/apis/perm")
public class PermEntryController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public PermEntryItemDTO getPermEntry(@RequestParam("descriptor") String descriptor) {
        return this.roleService.getPermEntryItemByDescriptor(descriptor);
    }

    @DeleteMapping
    public void deletePermEntry(@RequestParam("descriptor") String descriptor,
            @RequestParam(name = "recursive", defaultValue = "false") boolean recursive)
    {
        if(recursive) {
            this.roleService.removePermEntryRecursively(descriptor);
        } else {
            this.roleService.removePermEntry(descriptor);
        }
    }

    @PostMapping
    public void createPermEntry(@RequestBody @Valid PermEntryItemPostDTO entry) {
        PermEntryItemDTO pentry = new PermEntryItemDTO();
        MyBeanUtils.myCopyProperties(pentry, entry);
        this.roleService.addPermEntry(entry.getParentDescriptor(), pentry);
    }

    @PutMapping
    public void updatePermEntry(@RequestBody PermEntryItemPutDTO entry) {
        PermEntryItemDTO item = new PermEntryItemDTO();
        MyBeanUtils.myCopyProperties(item, entry);
        this.roleService.updatePermEntry(entry.getDescriptor(), item);
    }

    @GetMapping("/tree")
    public Collection<PermEntryTreeDTO> getPermEntryTree() {
        return this.roleService.getPermEntryTree();
    }

    @GetMapping("/role")
    public Collection<String> getPermEntryRoles(@RequestParam("descriptor") String descriptor) {
        Collection<String> ans = new ArrayList<>();
        this.roleService.getRolesByPermEntry(descriptor).forEach(role -> ans.add(role.getRoleName()));
        return ans;
    }
}

