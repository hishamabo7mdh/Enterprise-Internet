package com.example.dataSpring.service;


import com.example.dataSpring.model.MyFile;

import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.UserIp;
import com.example.dataSpring.repositry.MyFileRepo;
import com.example.dataSpring.repositry.PersonRepo;
import com.example.dataSpring.utils.upload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class MyFileService {
    @Autowired
    private MyFileRepo myFileRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private UserIPService userIPService;
    public MyFile getFile(Integer ID){
        Optional<MyFile> file=this.myFileRepo.findById(ID);
        return file.orElse(null);
    }
    public MyFile findByName(String name){
        return this.myFileRepo.findByName(name);
    }
    public void upload(MultipartFile multipartFile, Integer idOwner) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        MyFile myFile= MyFile.builder().name(fileName).status(false).pinding(false).build();
        FileUploadUtil.saveFile(fileName, multipartFile);
        Person p=personRepo.findById(idOwner).get();
        myFile.setOwner(p);
        if (myFileRepo.findByName(multipartFile.getOriginalFilename())==null)
            this.myFileRepo.save(myFile);
    }
    public void deleteFileById(Integer id){
        this.myFileRepo.deleteFileById(id);

        //this.myFileRepo.deleteFilesByOwnerId(ID);
    }

    public List<MyFile> getAllFile() {
        List<MyFile>myFileList=this.myFileRepo.findAll();
        System.out.println(myFileList);
        return myFileList;
    }

    public List<MyFile> getFilesPertson(Integer id) {
        return myFileRepo.findByOwnerId(id);
    }
    public void deleteAllFilesPerson(Integer id){
        myFileRepo.deleteFilesByOwnerId(id);

    }


}
