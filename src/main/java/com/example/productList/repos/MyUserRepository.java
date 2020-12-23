package com.example.productList.repos;

import com.example.productList.model.EventItem;
import com.example.productList.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);

    List<EventItem> findEventsByLogin(String login);
}
