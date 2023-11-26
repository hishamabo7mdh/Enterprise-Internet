package com.example.dataSpring.controller;

import com.example.dataSpring.model.Person;
import com.example.dataSpring.model.UserIp;
import com.example.dataSpring.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/index")
    public String getUser(ModelMap model){
        model.addAttribute("persons",personService.getAllUser());
        return "index";
    }
    @GetMapping("/login")
    public String login(ModelMap model){
        model.addAttribute("person",new Person());
        model.put("Err","");
        return "login";
    }

    @RequestMapping(value = "/register-person" , method = RequestMethod.GET)
    public String register(ModelMap model)
    {
        Person person=new Person();

        model.addAttribute("person",person);
        return "register";
    }
    @RequestMapping(value = "/register-person" , method = RequestMethod.POST)
    public String register(ModelMap model, @ModelAttribute("person") Person person, HttpServletRequest httpServletRequest)
    {
        person.setUserIp(UserIp.builder().ip(httpServletRequest.getRemoteAddr()).person(person).build());
        personService.savePerson(person);
        return "redirect:/person/index";
    }

    @RequestMapping(value = "/delete-person" , method = RequestMethod.GET)
    public String deletePerson(@RequestParam Integer id)
    {
         personService.deleteUser(id);
         return "redirect:/person/index";
    }
    @RequestMapping(value = "/update-person" , method = RequestMethod.GET)
    public String updatePerson(ModelMap model,@RequestParam Integer id)
    {
        model.addAttribute("person",this.personService.getUser(id));
        return "update";
    }
    @RequestMapping(value = "/update-person" , method = RequestMethod.POST)
    public String updatePerson(Person person)
    {
         personService.updatePerson(person);
         return "redirect:/files/home-file?id="+ person.getId();
    }

    @GetMapping("/getAll-person")
    public List<Person> getAllPerson()
    {
        return personService.getAllUser();
    }
}
