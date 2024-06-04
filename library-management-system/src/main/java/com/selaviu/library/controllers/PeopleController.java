package com.selaviu.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selaviu.library.dao.PersonDAO;
import com.selaviu.library.models.Person;

import jakarta.validation.Valid;

@RequestMapping(value = "/people")
@Controller
public class PeopleController {
    
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id).orElse(null));
        Person person = personDAO.show(id).orElse(null);
        System.out.println(person.toString());
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, 
                            BindingResult bindingResult){
        // personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id).orElse(null));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult, @PathVariable("id") int id){
        // personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
