package hello.admincontrol.controller;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.service.MeetingService;
import hello.admincontrol.service.dto.meeting.DayResponseDTO;
import hello.admincontrol.service.dto.meeting.LatestThirtyDayResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingDetailResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingPostDTO;


/**
 * 会议的增删改查接口
 */
@RestController
@RequestMapping("/apis/meeting")
public class MeetingController {
    @Autowired
    private MeetingService mtService;

    @PostMapping()
    private void createMeeting(@RequestBody @Valid MeetingPostDTO mt) {
        // TODO user
        this.mtService.createMeeting(mt);
    }

    @GetMapping()
    private MeetingDetailResponseDTO getMeeting(@RequestParam("meetingId") int meetingId) {
        // TODO user
        return mtService.meetingDetail(null, meetingId);
    }


    @GetMapping("/latest-thirty-days")
    private Collection<LatestThirtyDayResponseDTO> getLatestThirtyDays() {
        final String username = "ldy"; // TODO
        return this.mtService.latestCalenderThirtyDay(username);
    }

    @GetMapping("/day-list")
    private Collection<DayResponseDTO> getSpecificDay(
            @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        final String username = "ldy"; // TODO
        return this.mtService.calenderIn(username, date);
    }
}

