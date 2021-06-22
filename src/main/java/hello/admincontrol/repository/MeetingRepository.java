package hello.admincontrol.repository;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import hello.admincontrol.entity.Meeting;


public interface MeetingRepository extends PagingAndSortingRepository<Meeting, Long> {
    /**
     * 根据用户名查询时间段内的会议, 结果会议中的 sponsor 字段或者 users 字段包含
     * 查询的用户名并且会议在查询时间段内. 结果中的会议都不会重复
     * @param username  参加会议的用户
     * @param startDate 查询的开始时间
     * @param endDate   查询的结束时间
     */
    @Modifying
    @Query("SELECT DISTINCT mt FROM Meeting AS mt LEFT JOIN FETCH mt.users mtu " +
           "WHERE (mt.sponsor = ?1 OR mtu.name = ?1) AND (?2 <= date AND date <= ?3)")
    Collection<Meeting> findByUsers_NameOrSponsorAndDateBetween(String username, Date startDate, Date endDate);

    Optional<Meeting> findById(Long id);
}

