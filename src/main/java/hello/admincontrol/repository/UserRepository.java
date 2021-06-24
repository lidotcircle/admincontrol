package hello.admincontrol.repository;


import org.springframework.data.repository.CrudRepository;
import hello.admincontrol.entity.ScheduleAttachment;


public interface UserRepository extends CrudRepository<ScheduleAttachment, Long> {
}

