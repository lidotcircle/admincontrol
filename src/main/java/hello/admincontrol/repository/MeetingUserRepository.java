package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.MeetingUser;


public interface MeetingUserRepository extends PagingAndSortingRepository<MeetingUser, Long> {
}

