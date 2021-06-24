package hello.admincontrol.controller.meeting;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.service.MeetingService;
import hello.admincontrol.service.dto.meetingComment.MeetingCommentPostDTO;


@RestController
@RequestMapping("/apis/meeting/comment")
public class CommentController {
    @Autowired
    private MeetingService mtService;

    @PostMapping()
    private void addComment(@RequestBody @Valid MeetingCommentPostDTO mt) {
        MeetingComment mc = new MeetingComment();
        BeanUtils.copyProperties(mt, mc);
        mc.setTime(new Date());
        mc.setName("nobody"); // TODO

        this.mtService.addComment(null, mt.getMeetingId(), mc);
    }

    @DeleteMapping()
    private void deleteComment(@RequestParam() long meetingId, @RequestParam() int index) {
        this.mtService.removeComment(meetingId, index);
    }
}

