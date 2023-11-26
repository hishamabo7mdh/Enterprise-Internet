package com.example.dataSpring.controller;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.MyFile;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.UserIp;
import com.example.dataSpring.service.FileGroupService;
import com.example.dataSpring.service.MyFileService;
import com.example.dataSpring.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/fileGroup")
public class FileGroupController {
    @Autowired
    private FileGroupService fileGroupService;
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private PersonService personService;


    @GetMapping("/upload")
    public String fileHome(ModelMap model,@RequestParam Integer id,@RequestParam Integer groupId)
    {

        model.addAttribute("person",personService.getUser(id));
        model.addAttribute("files",fileGroupService.findfilesByGroupId(groupId));
        return "home";
    }

    @GetMapping("/Add-file")
    public String addFileToGroup(ModelMap model,@RequestParam Integer id,@RequestParam Integer groupId)
    {
        Group group=new Group();
        model.put("id",id);
        model.put("groupId",groupId);
        return "searchfiles";
    }

    @PostMapping("/Add-file")
    public String addPersonToGroup(@RequestParam Integer personId,@RequestParam Integer groupId,@RequestParam String name)
    {
        Integer fileIdByName=fileGroupService.findPersonIdByName(name);
        fileGroupService.addFileToGroup(fileIdByName,groupId);
        return "redirect:/group/home-group?id="+personId;
    }

}
