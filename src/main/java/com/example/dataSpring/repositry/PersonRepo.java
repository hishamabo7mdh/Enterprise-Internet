package com.example.dataSpring.repositry;

import com.example.dataSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {
//    List<>findByFilesFKId(Integer id);
    Person findByName(String name);
    Person findByPassword(String name);
    @Query("SELECT p.id FROM Person p WHERE p.name = ?1")
    Integer findPersonIdByName(String personName);


}
