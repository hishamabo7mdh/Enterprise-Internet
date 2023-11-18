package com.example.dataSpring.service;

import com.example.dataSpring.model.MyFile;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.UserIp;
import com.example.dataSpring.repositry.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
     private PersonRepo personRepo;
    @Autowired
    private UserIPService userIPService;

    @Autowired
    private MyFileService myFileService ;
    public Person getUser(Integer ID){
        Optional<Person> person=this.personRepo.findById(ID);
        return person.orElse(null);
    }
    public void savePerson(Person person)
    {
        UserIp userIp=person.getUserIp();
        person.setUserIp(null);
        Person p=this.personRepo.save(person);
        userIPService.create(userIp);
    }
    @Transactional
    public void deleteUser(Integer ID){
        this.myFileService.deleteAllFilesPerson(ID);
        UserIp userIp=this.userIPService.findByPersonId(ID);
        this.userIPService.delete(userIp.getId());
        this.personRepo.deleteById(ID);
    }
    public void updatePerson(Person person)
    {
        this.personRepo.save(person);

    }
    public List<Person> getAllUser() {
        return this.personRepo.findAll();
    }
    public Person check(Person person)
    {
        if (person!=null)
        {
            Person p=personRepo.findByName(person.getName());
            if (p!=null)
                return p;
        }
        return null;
    }
    public Integer findPersonIdByName(String personName){
        return personRepo.findPersonIdByName(personName);
    }
}
