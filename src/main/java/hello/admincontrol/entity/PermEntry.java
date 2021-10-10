package hello.admincontrol.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;


@Entity
@Table(name="tbl_perm_entry",
       uniqueConstraints = @UniqueConstraint(columnNames = {"link", "method"}))
public class PermEntry implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue @Column(name = "pk_perm_entry_id")
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    private String descriptor;
    public String getDescriptor() {
        return this.descriptor;
    }
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Column()
    private String permEntryName;
    public String getPermEntryName() {
        return this.permEntryName;
    }
    public void setPermEntryName(String permEntryName) {
        this.permEntryName = permEntryName;
    }

    private String link;
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Column(columnDefinition = "ENUM('ALL', 'POST', 'PUT', 'GET', 'DELETE') DEFAULT 'ALL'")
    private String method;
    public String getMethod() {
        return this.method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    @Column(columnDefinition = "ENUM('menu', 'page', 'button') DEFAULT 'menu'")
    private String entryType;
    public String getEntryType() {
        return this.entryType;
    }
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    @Lob
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

    @ManyToOne()
    private PermEntry parent;
    public PermEntry getParent() {
        return this.parent;
    }
    public void setParent(PermEntry parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    private Collection<PermEntry> children;
    public Collection<PermEntry> getChildren() {
        return children;
    }
    public void setChildren(Collection<PermEntry> children) {
        this.children = children;
    }

    @ManyToMany()
    private Collection<Role> roles;
    public Collection<Role> getRoles() {
        return this.roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

