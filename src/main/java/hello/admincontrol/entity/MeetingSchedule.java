package hello.admincontrol.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 会议的议程表, 和会议表是多对一的关系, 即每一个会议可以有多个议程
 */
@Entity
@Table(name = "tbl_meeting_schedule")
public class MeetingSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

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

    private String content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    private String createUser;
    public String getCreateUser() {
        return this.createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    private Date createTime;
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private int duration;
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    private int type;
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

    private int csort;
    public int getCsort() {
        return this.csort;
    }
    public void setCsort(int csort) {
        this.csort = csort;
    }

    @OneToMany(mappedBy = "schedule")
    private Collection<ScheduleAttachment> attachments;
    public Collection<ScheduleAttachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(Collection<ScheduleAttachment> attachments) {
        this.attachments = attachments;
    }
}

