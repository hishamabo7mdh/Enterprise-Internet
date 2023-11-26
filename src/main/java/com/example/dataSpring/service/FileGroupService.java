package com.example.dataSpring.service;

import com.example.dataSpring.model.*;
import com.example.dataSpring.repositry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileGroupService {
    @Autowired
    private FileGroupRepository filGroupRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MyFileRepo myFileRepo;

    @CacheEvict(cacheNames = "filesInGroup",allEntries = true)
    public void addFileToGroup(Integer fileID, Integer groupId)
    {
        Group group=groupRepository.findById(groupId).get();
        MyFile file=myFileRepo.findById(fileID).get();
        FileGroup fileGroup= FileGroup.builder().group(group).file(file).build();
        filGroupRepository.save(fileGroup);
    }
    @Modifying
    @Transactional
    @CacheEvict(cacheNames = "filesInGroup", allEntries = true)
    public void deleteFileToGroup(Integer fileID, Integer groupId)
    {
            filGroupRepository.removeFileFromGroup(fileID,groupId);
    }
    public Integer findPersonIdByName(String fileName)
    {
        return filGroupRepository.findPersonIdByName(fileName);
    }
    public List<MyFile> findfilesByGroupId(Integer groupId)
    {
        return filGroupRepository.findfilesByGroupId(groupId);
    }

}
