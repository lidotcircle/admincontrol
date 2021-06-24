package hello.admincontrol.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;


/**
 * 用户表
 */
@Entity
@Table(name="tbl_user",
       uniqueConstraints = @UniqueConstraint(columnNames = {"thirdPartyAccountType", "thirdPartyAccount"}))
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue @Column()
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "gmt_created", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdDate;
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    @Column(name = "gmt_modified", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date modifiedDate;
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Lob
    private byte[] profilePhoto;
    public byte[] getProfilePhoto() {
        return this.profilePhoto;
    }
    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Column(unique = true)
    private String phone;
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(unique = true)
    private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // 第三方账号信息
    @Column(columnDefinition = "ENUM('none', 'wechat', 'qq') NOT NULL DEFAULT 'none'")
    private String thirdPartyAccountType;
    public String getThirdPartyAccountType() {
        return this.thirdPartyAccountType;
    }
    public void setThirdPartyAccountType(String thirdPartyAccountType) {
        this.thirdPartyAccountType = thirdPartyAccountType;
    }

    @Column(columnDefinition = "VARCHAR(256) NULL DEFAULT NULL")
    private String thirdPartyAccount;
    public String getThirdPartyAccount() {
        return this.thirdPartyAccount;
    }
    public void setThirdPartyAccount(String thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }

    @Column()
    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

