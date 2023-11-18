package com.example.dataSpring.repositry;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.PersonGroup;
import com.example.dataSpring.model.UserIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonGroupRepository extends JpaRepository<PersonGroup,Integer> {
    @Query("SELECT pg.group FROM PersonGroup pg WHERE pg.person.id = ?1")
    List<Group> findGroupByPersonId(Integer personId);
    @Query("SELECT pg FROM PersonGroup pg WHERE pg.person.id = ?1 AND pg.group.id = ?2")
    PersonGroup findPersonInGroup(Integer personId , Integer groupId);
}
