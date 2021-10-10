package hello.admincontrol.controller.role;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import hello.admincontrol.exception.Forbidden;
import hello.admincontrol.service.RoleService;
import hello.admincontrol.service.dto.role.PermEntryItemDTO;
import hello.admincontrol.service.dto.role.RolePermEntryItemPostDTO;


@Tag(name = "角色权限配置")
@RestController
@RequestMapping("/apis/role/perm")
public class RolePermEntryController {
    @Autowired
    private RoleService roleService;

    @PostMapping()
    private void enablePerm(@RequestBody @Valid RolePermEntryItemPostDTO req)
    {
        if(req.getRecursive()) {
            this.roleService.enablePermEntryRecursively(req.getRoleName(), req.getDescriptor());
        } else {
            this.roleService.enablePermEntry(req.getRoleName(), req.getDescriptor());
        }
    }

    @DeleteMapping()
    private void disablePerm(@RequestParam("descriptor") String descriptor,
            @RequestParam("roleName") String roleName,
            @RequestParam(value = "recursive", defaultValue = "false") boolean recursive)
    {
        if(recursive) {
            this.roleService.disablePermEntryRecursively(roleName, descriptor);
        } else {
            this.roleService.disablePermEntry(roleName, descriptor);
        }
    }

    @GetMapping()
    private void getPerm(@RequestParam("descriptor") String descriptor,
                         @RequestParam("roleName") String roleName)
    {
        if(!this.roleService.hasPermission(roleName, descriptor)) {
            throw new Forbidden("角色无权限");
        }
    }

    @GetMapping("/list")
    public Collection<PermEntryItemDTO> getPermEntryRole(@RequestParam("roleName") String roleName) {
        return this.roleService.getPermEntries(roleName);
    }
}

