package com.example.dataSpring.service;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.repositry.GroupRepository;
import com.example.dataSpring.repositry.PersonGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonGroupRepository personGroupRepository;
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public List<Group> getAllGroup() {
        return this.groupRepository.findAll();
    }

    public Group getGroup(Integer ID) {
        Optional<Group> group = this.groupRepository.findById(ID);
        return group.orElse(null);
    }
    @Transactional
    public void deleteGroup(Integer groupId) {
        personGroupRepository.deleteFilesBygroupId(groupId);
        groupRepository.deleteById(groupId);
    }




}
