package hello.admincontrol.service.dto.meeting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hello.admincontrol.entity.Attachment;
import hello.admincontrol.entity.Meeting;
import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.entity.MeetingSchedule;
import hello.admincontrol.entity.MeetingUser;


/**
 * 某个会议的详情
 */
public class MeetingDetailResponseDTO extends DayResponseDTO
{
    private String sponsor;
    public String getSponsor() {
        return this.sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    private Collection<Attachment> attachments;
    public Collection<Attachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    private List<MeetingComment> comments;
    public List<MeetingComment> getComments() {
        return this.comments;
    }
    public void setComments(List<MeetingComment> comments) {
        this.comments = comments;
    }

    private Collection<MeetingSchedule> schedule;
    public Collection<MeetingSchedule> getSchedule() {
        return this.schedule;
    }
    public void setSchedule(Collection<MeetingSchedule> schedule) {
        this.schedule = schedule;
    }

    private Collection<MeetingUser> users;
    public Collection<MeetingUser> getUsers() {
        return this.users;
    }
    public void setUsers(Collection<MeetingUser> users) {
        this.users = users;
    }

    public MeetingDetailResponseDTO(Meeting mt) {
        super(mt);
        this.attachments = new ArrayList<>();

        mt.getAttachments().forEach(attach -> this.attachments.add(attach.getAttachment()));
    }
}

