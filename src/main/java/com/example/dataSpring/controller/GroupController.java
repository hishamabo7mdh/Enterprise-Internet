package com.example.dataSpring.controller;

import com.example.dataSpring.model.Group;
import com.example.dataSpring.model.PersonGroup;
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

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PersonGroupService personGroupService;
    @Autowired
    private PersonService personService;
    @GetMapping("/home-group")
    String indexGroup(ModelMap model , @RequestParam Integer id)
    {
        model.addAttribute("person",personService.getUser(id));
        System.out.println("Group: "+this.personGroupService.findGroupByPersonId(id));
        model.addAttribute("groups",this.personGroupService.findGroupByPersonId(id));
        return "homeGroup";
    }
    @GetMapping("/create-group")
    public String createGroup(ModelMap model,@RequestParam Integer id)
    {
        Group group=new Group();
        model.put("id",id);
        model.addAttribute("group",group);
        return "creatGroup";
    }

    @PostMapping("/create-group")
    public String createGroup(Group group, @RequestParam Integer personId)
    {
        this.groupService.saveGroup(group);
        personGroupService.addPerson(personId,group.getId());
        return "redirect:/group/home-group?id="+personId;
    }

}
