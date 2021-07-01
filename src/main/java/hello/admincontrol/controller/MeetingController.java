package hello.admincontrol.controller;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import hello.admincontrol.entity.Meeting;
import hello.admincontrol.entity.MeetingSchedule;
import hello.admincontrol.service.dto.meetingSchedule.MeetingSchedulePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import hello.admincontrol.service.MeetingService;
import hello.admincontrol.service.dto.meeting.DayResponseDTO;
import hello.admincontrol.service.dto.meeting.LatestThirtyDayResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingDetailResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingPostDTO;
import hello.admincontrol.service.dto.meeting.MeetingPutDTO;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * 会议的增删改查接口
 */
@Tag(name = "会议", description = "会议的增删改查")
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

    /**
     * 会议编辑接口
     * @param mt
     */
    @PostMapping("/edit")
    private void editMeeting(@RequestBody @Valid MeetingPutDTO mt){

        this.mtService.editMeeting(mt);
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

    @PostMapping("/{meetingId}/schedule")
    private MeetingSchedule addSchedule(
            @PathVariable Long meetingId,
            @RequestBody  MeetingSchedule schedule
            ){
        Meeting meeting = mtService.findMeetingById(meetingId).get();
        schedule.setMeeting(meeting);
        return mtService.addSchedule(schedule);
    }
}

