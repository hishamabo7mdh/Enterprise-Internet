package com.example.dataSpring.service;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.repositry.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;


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

    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }




}
