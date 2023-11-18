package com.example.dataSpring.repositry;

import com.example.dataSpring.model.UserIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIPRepository extends JpaRepository<UserIp,Integer> {
    UserIp findByPersonId(Integer id);
}
