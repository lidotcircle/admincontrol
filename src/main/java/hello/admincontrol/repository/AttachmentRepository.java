package hello.admincontrol.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.Attachment;


public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Long> {
}

