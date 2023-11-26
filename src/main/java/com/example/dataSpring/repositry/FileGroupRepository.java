package com.example.dataSpring.repositry;

import com.example.dataSpring.model.FileGroup;
import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FileGroupRepository extends JpaRepository<FileGroup,Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM FileGroup fg WHERE fg.file.id = :fileId AND fg.group.id = :groupId")
    void removeFileFromGroup(@Param("fileId") Integer fileId, @Param("groupId") Integer groupId);
    @Query("SELECT f.id FROM MyFile f WHERE f.name = ?1")
    Integer findPersonIdByName(String fileName);

    @Query("SELECT fg.file FROM FileGroup fg WHERE fg.group.id = ?1")
    List<MyFile> findfilesByGroupId(Integer groupId);
}
