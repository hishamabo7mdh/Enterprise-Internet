package com.example.dataSpring.controller;

import com.example.dataSpring.model.MyFile;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.service.MyFileService;
import com.example.dataSpring.service.PersonService;
import com.example.dataSpring.utils.download.FileDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/files")
public class MyFileController {
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private PersonService personService;


    @GetMapping("/save-file")
    public String upload(ModelMap model, @RequestParam Integer id)
    {
        model.put("id",id);
        return "uploadFile";
    }
    @PostMapping("/save-file")
    public String upload(@RequestParam("multipartFile") MultipartFile multipartFile, @RequestParam Integer id) throws IOException {
        String msg;
        try {
            myFileService.upload(multipartFile,id);
            msg="Upload the file successfully: "+multipartFile.getOriginalFilename();

        }catch (Exception e) {
            System.out.println(e);
            msg = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
        }
        System.out.println(msg);
        return "redirect:/files/home-file?id=" + id;
    }
    @GetMapping("download-file")
    public ResponseEntity<?> downloadFile(ModelMap model,@RequestParam Integer id)
    {
        MyFile myFile=myFileService.getFile(id);
        FileDownloadUtil downloadUtil=new FileDownloadUtil();
        Resource fileResource=null;
        try {
            fileResource=downloadUtil.getFileAsResource(myFile.getName());
            model.addAttribute("fileResource", fileResource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + fileResource.getFilename() + "\"";

        return         ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(fileResource);
    }
    @PostMapping("/login")
    public String login(ModelMap model,@ModelAttribute("person") Person person){
        Person per=personService.check(person);
        return home(model,per.getId());
    }
    @GetMapping("/home-file")
    public String home(ModelMap model,@RequestParam Integer id)
    {
        List<MyFile>myFileList=myFileService.getFilesPertson(id);
        model.addAttribute("person",personService.getUser(id));
        model.addAttribute("files",myFileList);
        return "home";
    }


    @GetMapping("/delete-file")
    public String deleteUser(ModelMap model,@RequestParam Integer id,@RequestParam Integer idPerson)
    {
        myFileService.deleteFileById(id);

        return home(model,idPerson);
    }

    @GetMapping("/update-file")
    public MyFile updateUser(@RequestBody MyFile myFile)
    {
        //System.out.println("update");
        //return myFileService.saveUser(myFile);
        return null;
    }

   /*@GetMapping("/getUserByperson-file")
    public List<MyFile> getDepart(@RequestParam Integer ID)
    {
        return myFileService.getDepart(ID);
    }*/

}
