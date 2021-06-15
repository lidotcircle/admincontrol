package hello.admincontrol.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_schedule_attachment")
@IdClass(ScheduleAttachment.ScheduleAttachmentId.class)
public class ScheduleAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

    @Embeddable
    static class ScheduleAttachmentId implements Serializable {
		private static final long serialVersionUID = 1L;

        private long schedule;
        public long getSchedule() {
            return this.schedule;
        }
        public void setSchedule(long schedule) {
            this.schedule = schedule;
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
            if(!(uc instanceof ScheduleAttachmentId)) {
                return false;
            }
            final ScheduleAttachmentId uuc = (ScheduleAttachmentId) uc;
            return this.schedule == uuc.schedule && this.attachment == uuc.attachment;
        }

        @Override
        public int hashCode() {
            return (int)(this.schedule + (this.attachment << 16));
        }
    }

    @Id @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private MeetingSchedule schedule;
    public MeetingSchedule getSchedule() {
        return this.schedule;
    }
    public void setSchedule(MeetingSchedule schedule) {
        this.schedule = schedule;
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

