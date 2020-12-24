package com.example.productList.repos;

import com.example.productList.model.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<EventItem, Long> {
    @Query(value = "SELECT * FROM EVENT_ITEM " +
            "WHERE  extract(MONTH FROM END) = :m " +
            "AND extract(DAY FROM END) = :d " +
            "AND extract(YEAR FROM END) = :y",
            nativeQuery = true)
    List<EventItem> findByEndMatch(@Param("d") int day, @Param("m") int month, @Param("y") int year);

    @Query(value = "SELECT * FROM EVENT_ITEM " +
            "WHERE  extract(MONTH FROM START) = :m " +
            "AND extract(DAY FROM START) = :d " +
            "AND extract(YEAR FROM START) = :y",
            nativeQuery = true)
    List<EventItem> findByStartMatch(@Param("d") int day, @Param("m") int month, @Param("y") int year);

//    @Query(value = "select MY_USER.LOGIN from MY_USER inner join MY_USER_EVENTS MUE on MY_USER.ID = MUE.MY_USER_ID inner join EVENT_ITEM EI on EI.ID = MUE.EVENTS_ID and EI.ID= (:itemFromEvent)", nativeQuery = true)
//    String findMyUserByEvent(@Param("itemFromEvent") int itemFromEvent);

    @Query(value="select EVENT_ITEM.ID, END, NAME, START, STATUS, USER_ID from EVENT_ITEM inner join MY_USER mu on mu.id = EVENT_ITEM.USER_ID and mu.LOGIN = (:userLogin)", nativeQuery = true)
    List<EventItem> findEventsByLogin(@Param("userLogin") String userLogin);
}
