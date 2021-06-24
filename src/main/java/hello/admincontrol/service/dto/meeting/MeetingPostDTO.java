package hello.admincontrol.service.dto.meeting;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import hello.admincontrol.entity.Attachment;
import hello.admincontrol.entity.MeetingSchedule;


/**
 * 会议创建DTO
 */
public class MeetingPostDTO
{
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date date;
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @NotNull
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

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date startTime;
    public Date getStartTime() {
        return this.startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
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

    @NotNull
    @Pattern(regexp = ".{1,}")
    private String sponsor;
    public String getSponsor() {
        return this.sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    private Collection<MeetingSchedule> schedules;
    public Collection<MeetingSchedule> getSchedules() {
        return this.schedules;
    }
    public void setSchedules(Collection<MeetingSchedule> schedules) {
        this.schedules = schedules;
    }
}

