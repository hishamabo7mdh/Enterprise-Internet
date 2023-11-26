package com.example.dataSpring.repositry;

import com.example.dataSpring.model.MyFile;
import com.example.dataSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MyFileRepo extends JpaRepository<MyFile,Integer> {
    //List<MyFile>findByPersonId(Integer id);
    List<MyFile> findByOwnerId(Integer id);
    MyFile findByName(String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM MyFile WHERE owner.id = :personId")
    void deleteFilesByOwnerId(@Param("personId") Integer personId);


    @Transactional
    @Modifying
    @Query("DELETE FROM MyFile WHERE id = :fileId")
    void deleteFileById(@Param("fileId") Integer fileId);

}
