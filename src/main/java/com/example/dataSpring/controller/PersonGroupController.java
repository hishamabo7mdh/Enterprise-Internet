package com.example.dataSpring.controller;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.Person;
import com.example.dataSpring.service.FileGroupService;
import com.example.dataSpring.service.GroupService;
import com.example.dataSpring.service.PersonGroupService;
import com.example.dataSpring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/personGroup")
public class PersonGroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PersonGroupService personGroupService;
    @Autowired
    private PersonService personService;
    @Autowired
    private FileGroupService fileGroupService;
    @GetMapping("/show-person-group")
    String indexGroup(ModelMap model , @RequestParam Integer id, @RequestParam Integer groupId)
    {
        model.addAttribute("files",fileGroupService.findfilesByGroupId(groupId));
        model.addAttribute("person",personService.getUser(id));
        model.addAttribute("persons",personGroupService.findPeopleByGroupId(groupId));
        model.put("groupId",groupId);
        return "showPeople";
    }
    @GetMapping("/Add-person")
    public String addPersonToGroup(ModelMap model,@RequestParam Integer id,@RequestParam Integer groupId)
    {
        Group group=new Group();
        model.put("id",id);
        model.put("groupId",groupId);
        model.addAttribute("person",new Person());
        return "searchPerson";
    }

    @PostMapping("/Add-person")
    public String addPersonToGroup(@RequestParam Integer personId,@RequestParam Integer groupId,@RequestParam String name)
    {
        System.out.println(name);
        Integer personIdByName=personService.findPersonIdByName(name);
        personGroupService.addPerson(personIdByName,groupId);
        return "redirect:/group/home-group?id="+personId;
    }


}
