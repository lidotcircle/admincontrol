package hello.admincontrol.service.dto.meeting;

import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Date;

import hello.admincontrol.entity.Attachment;
import hello.admincontrol.entity.MeetingSchedule;


/**
 * 会议修改
 */
public class MeetingPutDTO extends MeetingPostDTO
{
    @NotNull
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    private Date date;
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
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

    private Collection<Attachment> attachments;
    public Collection<Attachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    private String sponsor;
    public String getSponsor() {
        return this.sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    private Collection<MeetingSchedule> schedule;
    public Collection<MeetingSchedule> getSchedule() {
        return this.schedule;
    }
    public void setSchedule(Collection<MeetingSchedule> schedule) {
        this.schedule = schedule;
    }
}

