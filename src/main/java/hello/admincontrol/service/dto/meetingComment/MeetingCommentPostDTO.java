package hello.admincontrol.service.dto.meetingComment;

import javax.validation.constraints.NotNull;


/**
 * 用于创建会议的评论
 */
public class MeetingCommentPostDTO //{
{
    @NotNull
    private long meetingId;
    public long getMeetingId() {
        return this.meetingId;
    }
    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    @NotNull
    private String comment;
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
} //}

