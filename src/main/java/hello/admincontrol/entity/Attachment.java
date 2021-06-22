package hello.admincontrol.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 附件表, 被会议附件表和议程附件表应用
 * 注意附件表并没用真正存有文件数据, 只是包含了一个文件的应用.
 */
@Entity
@Table(name = "tbl_attachment")
public class Attachment implements Serializable {
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

    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private long size;
    public long getSize() {
        return this.size;
    }
    public void setSize(long size) {
        this.size = size;
    }

    @OneToMany(mappedBy = "attachment")
    private Collection<MeetingAttachment> meetings;
    public Collection<MeetingAttachment> getMeetings() {
        return this.meetings;
    }
    public void setMeetings(Collection<MeetingAttachment> meetings) {
        this.meetings = meetings;
    }

    @OneToMany(mappedBy = "attachment")
    private Collection<ScheduleAttachment> schedules;
    public Collection<ScheduleAttachment> getSchedules() {
        return this.schedules;
    }
    public void setSchedules(Collection<ScheduleAttachment> schedules) {
        this.schedules = schedules;
    }
}

