package hello.admincontrol.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import hello.admincontrol.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.userName LIKE %?1% OR u.name LIKE %?1% OR u.phone LIKE %?1%")
    Page<User> findAll(String filter, Pageable page);
    Page<User> findAll(Pageable page);

    @Transactional
    void deleteByUserName(String userName);
}

