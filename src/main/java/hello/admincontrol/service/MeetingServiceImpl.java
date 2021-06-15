package hello.admincontrol.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import hello.admincontrol.exception.NotFound;


@Service
public class MeetingServiceImpl implements MeetingService {

	@Override
	public Collection<MeetingShort> latestCalenderThirtyDay(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<MeetingMedium> calenderIn(String username, Date day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeetingDetail meetingDetail(String username, long meetingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMeeting(MeetingCreation meeting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editMeeting(MeetingEdition meeting) throws NotFound {
		// TODO Auto-generated method stub
		
	}

}

