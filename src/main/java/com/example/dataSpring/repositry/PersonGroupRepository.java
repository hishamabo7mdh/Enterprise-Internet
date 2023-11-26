package com.example.dataSpring.repositry;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.PersonGroup;
import com.example.dataSpring.model.UserIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonGroupRepository extends JpaRepository<PersonGroup,Integer> {
    @Query("SELECT pg.group FROM PersonGroup pg WHERE pg.person.id = ?1")
    List<Group> findGroupByPersonId(Integer personId);
    @Query("SELECT pg FROM PersonGroup pg WHERE pg.person.id = ?1 AND pg.group.id = ?2")
    PersonGroup findPersonInGroup(Integer personId , Integer groupId);

    @Query("SELECT pg.person FROM PersonGroup pg WHERE pg.group.id = ?1")
    List<Person> findPeopleByGroupId(Integer groupId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PersonGroup pg WHERE pg.group.id = ?1")
    void deleteFilesBygroupId(Integer groupId);
}
