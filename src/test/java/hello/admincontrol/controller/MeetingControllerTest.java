package hello.admincontrol.controller;

import hello.admincontrol.service.MeetingService;
import hello.admincontrol.service.dto.meeting.MeetingPostDTO;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.comparator.JSONCompareUtil;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MeetingControllerTest {

    @Autowired
    private MeetingService mtService;

    @Test
    void CreateMeeting()
    {
//        Date BeginTime = new Date(2021,12,12,14,14,14);
//        Date EndTime = new Date(2021,12,22,14,14,14);
//        MeetingPostDTO meetingPostDTO = new MeetingPostDTO();
//        meetingPostDTO.setDate(BeginTime);
//        meetingPostDTO.setTitle("ac1");
//        meetingPostDTO.setStartTime(BeginTime);
//        meetingPostDTO.setContent("fzu");
//        meetingPostDTO.setMark(3);
//        meetingPostDTO.setEndTime(EndTime);
//        meetingPostDTO.setSponsor("hu");
//        mtService.createMeeting(meetingPostDTO);
        System.out.println("hello");

    }

}