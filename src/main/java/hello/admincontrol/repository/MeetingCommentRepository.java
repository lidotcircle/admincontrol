package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.MeetingComment;


public interface MeetingCommentRepository extends PagingAndSortingRepository<MeetingComment, Long> {
}

