package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.MeetingAttachment;


public interface MeetingAttachmentRepository extends PagingAndSortingRepository<MeetingAttachment, Long> {
}

