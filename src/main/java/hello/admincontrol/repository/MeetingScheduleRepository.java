package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.MeetingSchedule;


public interface MeetingScheduleRepository extends PagingAndSortingRepository<MeetingSchedule, Long> {
}

