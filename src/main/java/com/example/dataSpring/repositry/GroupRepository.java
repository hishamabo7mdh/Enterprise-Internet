package com.example.dataSpring.repositry;

import com.example.dataSpring.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Query("SELECT pg.group FROM PersonGroup pg WHERE pg.person.id = ?1")
    List<Group> findGroupByPersonId(Integer personId);
}
