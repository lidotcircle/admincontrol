package hello.admincontrol.controller.meeting;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.service.MeetingService;
import hello.admincontrol.utils.ObjUtil;


@RestController
@RequestMapping("/apis/meeting/comment")
public class Comment {
    @Autowired
    private MeetingService mtService;

    static class CommentCreationDTO //{
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
    @PostMapping()
    private void addComment(@RequestBody @Valid CommentCreationDTO mt) {
        MeetingComment mc = new MeetingComment();
        ObjUtil.assignFields(mc, mt);
        mc.setTime(new Date());
        mc.setName("nobody"); // TODO

        this.mtService.addComment(null, mt.meetingId, mc);
    }

    @DeleteMapping()
    private void deleteComment(@RequestParam() long meetingId, @RequestParam() int index) {
        this.mtService.removeComment(meetingId, index);
    }
}

