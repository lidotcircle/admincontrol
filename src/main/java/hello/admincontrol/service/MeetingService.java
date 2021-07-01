package hello.admincontrol.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import hello.admincontrol.entity.Meeting;
import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.entity.MeetingSchedule;
import hello.admincontrol.exception.NotFound;
import hello.admincontrol.service.dto.meeting.DayResponseDTO;
import hello.admincontrol.service.dto.meeting.LatestThirtyDayResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingDetailResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingPostDTO;
import hello.admincontrol.service.dto.meeting.MeetingPutDTO;


public interface MeetingService {

    /** 30天内的会议列表(简略) */
    Collection<LatestThirtyDayResponseDTO> latestCalenderThirtyDay(String username);

    /** 某天内的会议 */
    Collection<DayResponseDTO> calenderIn(String username, Date day);

    /** 某个会议的详情 */
    MeetingDetailResponseDTO meetingDetail(String username, long meetingId);

    /** 创建会议 */
    void createMeeting(MeetingPostDTO meeting);

    /** 编辑会议 */
    void editMeeting(MeetingPutDTO meeting);

    Optional<Meeting> findMeetingById(Long meetingId);

    /** 添加会议的评论 */
    void addComment(String username, long meetingId, MeetingComment comment);

    /** 删除会议的评论 */
    void removeComment(long meetingId, int index);

    /** 添加议程*/
    MeetingSchedule addSchedule( MeetingSchedule schedule);

}

