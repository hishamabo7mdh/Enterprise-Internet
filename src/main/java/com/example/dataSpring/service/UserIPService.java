package com.example.dataSpring.service;

import com.example.dataSpring.model.UserIp;
import com.example.dataSpring.repositry.UserIPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIPService {
    @Autowired
    UserIPRepository userIPRepository;
    public void create(UserIp userIp){
        userIPRepository.save(userIp);
    }
    public void  delete(Integer id)
    {
        userIPRepository.deleteById(id);
    }
    public  UserIp findByPersonId(Integer id){
        return userIPRepository.findByPersonId(id);
    }
}
