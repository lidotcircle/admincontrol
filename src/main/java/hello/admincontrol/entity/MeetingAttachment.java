package hello.admincontrol.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 会议附件表, 引用了会议表和附件表, 都是 多对一 的关系.
 * 可以看成会议和附件多对多中关系属性.
 * 会议中添加删除附件需要操作这张表.
 */
@Entity
@Table(name = "tbl_meeting_attachment")
@IdClass(MeetingAttachment.MeetingAttachmentId.class)
public class MeetingAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

    @Embeddable
    static class MeetingAttachmentId implements Serializable {
		private static final long serialVersionUID = 1L;

        private long meeting;
        public long getMeeting() {
            return this.meeting;
        }
        public void setMeeting(long meeting) {
            this.meeting = meeting;
        }

        private long attachment;
        public long getAttachment() {
            return this.attachment;
        }
        public void setAttachment(long attachment) {
            this.attachment = attachment;
        }

        @Override
        public boolean equals(Object uc) {
            if(!(uc instanceof MeetingAttachmentId)) {
                return false;
            }
            final MeetingAttachmentId uuc = (MeetingAttachmentId) uc;
            return this.meeting == uuc.meeting && this.attachment == uuc.attachment;
        }

        @Override
        public int hashCode() {
            return (int)(this.meeting + (this.attachment << 16));
        }
    }

    @Id @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    private Meeting meeting;
    public Meeting getMeeting() {
        return this.meeting;
    }
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    @Id @ManyToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private Attachment attachment;
    public Attachment getAttachment() {
        return this.attachment;
    }
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}

