package com.example.productList.repos;

import com.example.productList.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);

    @Query(value = "select * from MyUser where MyUser.ID = givenId:", nativeQuery = true)
    MyUser findMyUserByEvent(@Param("givenId") int givenId);
}
