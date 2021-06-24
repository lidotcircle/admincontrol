package hello.admincontrol.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.admincontrol.entity.Attachment;
import hello.admincontrol.entity.Meeting;
import hello.admincontrol.entity.MeetingAttachment;
import hello.admincontrol.entity.MeetingComment;
import hello.admincontrol.entity.MeetingSchedule;
import hello.admincontrol.entity.MeetingUser;
import hello.admincontrol.entity.ScheduleAttachment;
import hello.admincontrol.exception.Forbidden;
import hello.admincontrol.exception.NotFound;
import hello.admincontrol.repository.AttachmentRepository;
import hello.admincontrol.repository.MeetingAttachmentRepository;
import hello.admincontrol.repository.MeetingCommentRepository;
import hello.admincontrol.repository.MeetingRepository;
import hello.admincontrol.repository.MeetingScheduleRepository;
import hello.admincontrol.repository.MeetingUserRepository;
import hello.admincontrol.repository.ScheduleAttachmentRepository;
import hello.admincontrol.service.MeetingService;
import hello.admincontrol.service.dto.meeting.DayResponseDTO;
import hello.admincontrol.service.dto.meeting.LatestThirtyDayResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingDetailResponseDTO;
import hello.admincontrol.service.dto.meeting.MeetingPostDTO;
import hello.admincontrol.service.dto.meeting.MeetingPutDTO;


@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository mtResp;
    @Autowired
    private AttachmentRepository atResp;
    @Autowired
    private MeetingAttachmentRepository mtatResp;
    @Autowired
    private ScheduleAttachmentRepository scatResp;
    @Autowired
    private MeetingUserRepository mtusrResp;
    @Autowired
    private MeetingCommentRepository mtcmResp;
    @Autowired
    private MeetingScheduleRepository mtscResp;


    private Collection<Meeting> meetingsBetween(String username, Date start, Date end) {
        final Collection<Meeting> qlist = this.mtResp.findByUsers_NameOrSponsorAndDateBetween(username, start, end);
        return qlist;
    }

	@Override
	public Collection<LatestThirtyDayResponseDTO> latestCalenderThirtyDay(String username) {
        final Instant nowins = Instant.now().truncatedTo(ChronoUnit.DAYS);
        final Date now = Date.from(nowins);
        final Date end = Date.from(nowins.plus(30, ChronoUnit.DAYS));

        final Collection<Meeting> qlist = this.meetingsBetween(username, now, end);
        final Collection<LatestThirtyDayResponseDTO> ans = new ArrayList<>();
        qlist.forEach(meeting -> ans.add(new LatestThirtyDayResponseDTO(meeting)));

        return ans;
	}

	@Override
	public Collection<DayResponseDTO> calenderIn(String username, Date day) {
        final Instant dateins = day.toInstant().truncatedTo(ChronoUnit.DAYS);
        final Date start = Date.from(dateins);
        final Date end =   Date.from(dateins.plus(1, ChronoUnit.DAYS));

        final Collection<Meeting> qlist = this.meetingsBetween(username, start, end);
        final Collection<DayResponseDTO> ans = new ArrayList<>();
        qlist.forEach(meeting -> ans.add(new DayResponseDTO(meeting)));

        return ans;
	}


    private boolean isMemberOfMeeting(Meeting mt, String username) {
        if(mt.getSponsor().equals(username)) {
            return true;
        }

        for(MeetingUser u: mt.getUsers()) {
            u.getName().equals(username);
            return true;
        }

        return false;
    }
	@Override
	public MeetingDetailResponseDTO meetingDetail(String username, long meetingId) {
        Meeting mt = this.mtResp.findById(meetingId).orElseThrow(() -> new NotFound());
        if(username != null && !this.isMemberOfMeeting(mt, username)) {
            throw new Forbidden();
        }
		return new MeetingDetailResponseDTO(mt);
	}

    private MeetingAttachment addMeetingAttachment(Attachment at, Meeting mt) //{
    {
        final Attachment att = this.atResp.save(at);
        final MeetingAttachment ans = new MeetingAttachment();
        ans.setAttachment(att);
        ans.setMeeting(mt);

        return this.mtatResp.save(ans);
    } //}
    private ScheduleAttachment addScheduleAttachment(Attachment at, MeetingSchedule sc) //{
    {
        final Attachment att = this.atResp.save(at);
        final ScheduleAttachment ans = new ScheduleAttachment();
        ans.setAttachment(att);
        ans.setSchedule(sc);

        return this.scatResp.save(ans);
    } //}
    private MeetingSchedule addMeetingSchedule(MeetingSchedule sc, Meeting mt) //{
    {
        final MeetingSchedule scc = this.mtscResp.save(sc);
        for(ScheduleAttachment scat: sc.getAttachments()) {
        }

        return null;
    } //}

	@Override
	public void createMeeting(MeetingPostDTO meeting) {
        final Meeting mt = new Meeting();
        BeanUtils.copyProperties(meeting, mt);

        this.mtResp.save(mt);
	}

	@Override
	public void editMeeting(MeetingPutDTO meeting) throws NotFound {
		// TODO Auto-generated method stub
	}

	@Override
	public void addComment(String username, long meetingId, MeetingComment comment) //{
    {
        final Meeting mt = this.mtResp.findById(meetingId)
            .orElseThrow(() -> new NotFound());
        if(username != null && !this.isMemberOfMeeting(mt, username)) {
            throw new Forbidden();
        }

        comment.setName(username);
        comment = this.mtcmResp.save(comment);
        if(mt.getComments() == null) {
            mt.setComments(new ArrayList<>());
        }
        mt.getComments().add(comment);
        this.mtResp.save(mt);
	} //}

	@Override
    public void removeComment(long meetingId, int index) //{
    {
        final Meeting mt = this.mtResp.findById(meetingId)
            .orElseThrow(() -> new NotFound());

        var comments = mt.getComments();
        if(comments == null || comments.size() <= index) {
            throw new Forbidden();
        }
        comments.remove(index);

        this.mtResp.save(mt);
    } //}
}

