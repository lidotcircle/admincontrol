package hello.admincontrol.service.dto.role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class PermEntryItemPostDTO
{
    @NotNull
    @Pattern(regexp = "([a-zA-Z][a-zA-Z0-9_]{1,}\\.)*[a-zA-Z][a-zA-Z0-9_]{1,}")
    private String descriptor;
    public String getDescriptor() {
        return this.descriptor;
    }
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Pattern(regexp = "([a-zA-Z][a-zA-Z0-9_]{1,}\\.)*[a-zA-Z][a-zA-Z0-9_]{1,}")
    private String parentDescriptor;
    public String getParentDescriptor() {
        return this.parentDescriptor;
    }
    public void setParentDescriptor(String parentDescriptor) {
        this.parentDescriptor = parentDescriptor;
    }

    @NotNull
    @Pattern(regexp = "(\\/[a-zA-Z][a-zA-Z0-9@_\\-]+)+\\/?")
    private String link;
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Pattern(regexp = "POST|PUT|GET|DELETE|ALL")
    private String method;
    public String getMethod() {
        return this.method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    private String remark;
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String sortOrder;
    public String getSortOrder() {
        return this.sortOrder;
    }
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @NotNull
    @Pattern(regexp = "(menu|page|button)")
    private String entryType;
    public String getEntryType() {
        return this.entryType;
    }
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    @NotNull
    private String permEntryName;
    public String getPermEntryName() {
        return this.permEntryName;
    }
    public void setPermEntryName(String permEntryName) {
        this.permEntryName = permEntryName;
    }
}

