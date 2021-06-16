package hello.admincontrol.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_meeting")
public class Meeting implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "gmt_created", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdDate;
    @Column(name = "gmt_modified", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date modifiedDate;

    @Column(nullable = false)
    private Date date;
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(nullable = false)
    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private int status;
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    private int mark;
    public int getMark() {
        return this.mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }

    private String content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    private Date startTime;
    public Date getStartTime() {
        return this.startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private Date endTime;
    public Date getEndTime() {
        return this.endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private String location;
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Column(nullable = false)
    private String sponsor;
    public String getSponsor() {
        return this.sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    @OneToMany(mappedBy = "meeting")
    private Collection<MeetingAttachment> attachments;
    public Collection<MeetingAttachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(Collection<MeetingAttachment> attachments) {
        this.attachments = attachments;
    }

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "meeting_id")
    private List<MeetingComment> comments;
    public List<MeetingComment> getComments() {
        return this.comments;
    }
    public void setComments(List<MeetingComment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "meeting")
    private Collection<MeetingSchedule> schedule;
    public Collection<MeetingSchedule> getSchedule() {
        return this.schedule;
    }
    public void setSchedule(Collection<MeetingSchedule> schedule) {
        this.schedule = schedule;
    }

    @OneToMany(mappedBy = "meeting")
    private Collection<MeetingUser> users;
    public Collection<MeetingUser> getUsers() {
        return this.users;
    }
    public void setUsers(Collection<MeetingUser> users) {
        this.users = users;
    }
}

