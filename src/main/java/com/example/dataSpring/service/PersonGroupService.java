package com.example.dataSpring.service;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.PersonGroup;
import com.example.dataSpring.repositry.PersonGroupRepository;
import com.example.dataSpring.repositry.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonGroupService {
    @Autowired
    private PersonGroupRepository personGroupRepository;
    @Autowired
    private PersonRepo personRepo;
    @Autowired

    private GroupService groupService;
    public void addPerson(Integer personId , Integer groupId)
    {
        Person person=personRepo.findById(personId).get();
        Group group=groupService.getGroup(groupId);
        PersonGroup personGroup= PersonGroup.builder().person(person).group(group).build();
        this.personGroupRepository.save(personGroup);
    }
    public  void removePerson(Integer personId , Integer groupId)
    {
        PersonGroup personGroup=this.personGroupRepository.findPersonInGroup(personId,groupId);
        this.personGroupRepository.delete(personGroup);
    }
    public List<Group> findGroupByPersonId(Integer personId)
    {
        return this.personGroupRepository.findGroupByPersonId(personId);
    }


}
