package hello.admincontrol.repository;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import hello.admincontrol.entity.Meeting;


public interface MeetingRepository extends PagingAndSortingRepository<Meeting, Long> {
    @Modifying
    @Query("SELECT DISTINCT mt.* FROM Meeting AS mt RIGHT JOIN MeetingUser AS mtu ON mt.id = mtu.meetingId " +
           "WHERE (mt.sponsor = ?1 OR mtu.name = ?1) AND (?2 <= date AND date <= ?3)")
    Collection<Meeting> findByUsers_NameOrSponsorAndDateBetween(String username, Date startDate, Date endDate);

    Optional<Meeting> findById(Long id);
}

