package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.ScheduleAttachment;


public interface ScheduleAttachmentRepository extends PagingAndSortingRepository<ScheduleAttachment, Long> {
}

