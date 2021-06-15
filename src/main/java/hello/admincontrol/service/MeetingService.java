package hello.admincontrol.service;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import hello.admincontrol.entity.Attachment;
import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.entity.MeetingSchedule;
import hello.admincontrol.entity.MeetingUser;
import hello.admincontrol.exception.NotFound;


public interface MeetingService {

    public static class MeetingShort //{
    {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
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
    } //}
    Collection<MeetingShort> latestCalenderThirtyDay(String username);

    public static class MeetingMedium extends MeetingShort //{
    {
        private int status;
        public int getStatus() {
            return this.status;
        }
        public void setStatus(int status) {
            this.status = status;
        }

        private long id;
        public long getId() {
            return this.id;
        }
        public void setId(long id) {
            this.id = id;
        }

        private String content;
        public String getContent() {
            return this.content;
        }
        public void setContent(String content) {
            this.content = content;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        private Date startTime;
        public Date getStartTime() {
            return this.startTime;
        }
        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

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
    } //}
    Collection<MeetingMedium> calenderIn(String username, Date day);

    public static class MeetingDetail extends MeetingMedium //{
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

        private Collection<MeetingComment> comments;
        public Collection<MeetingComment> getComments() {
            return this.comments;
        }
        public void setComments(Collection<MeetingComment> comments) {
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
    } //}
    MeetingDetail meetingDetail(String username, long meetingId);

    public static class MeetingCreation //{
    {
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
    } //}
    void createMeeting(MeetingCreation meeting);

    public static class MeetingEdition extends MeetingCreation //{
    {
        private long id;
        public long getId() {
            return this.id;
        }
        public void setId(long id) {
            this.id = id;
        }
    } //}
    void editMeeting(MeetingEdition meeting) throws NotFound;

}

