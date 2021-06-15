package hello.admincontrol.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbl_meeting_user")
public class MeetingUser implements Serializable {
	private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id @GeneratedValue
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    private Meeting meeting;
    public Meeting getMeeting() {
        return this.meeting;
    }
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String avatar;
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private int status;
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}

