package com.spring_sql.sql_work.controller;


import com.spring_sql.sql_work.model.Cow;
import com.spring_sql.sql_work.model.CowHorseJoin;
import com.spring_sql.sql_work.repository.CowRepository;
import com.spring_sql.sql_work.service.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cow")
public class CowController {
    @Autowired
    CowRepository cowRepository;
    @Autowired
    CowService cowService;

    @GetMapping("/jpaget")
    public List<Cow> getCows() {
        return cowService.getCriteriaCow();
    }

    @GetMapping("/criteria")
    public List<String> getCowsNames() {
        return cowService.getCowsCriteriaSum();
    }

    @GetMapping("/sum")
    public Integer sumCowAge() {
        return cowService.ageSum();
    }

    @GetMapping
    public List<Cow> getAllCows() {
        return cowRepository.findAll();
    }

    @GetMapping("/one/{id}")
    public Optional<Cow> getCow(@PathVariable Long id) {
        return cowRepository.findById(id);
    }

    @PostMapping
    public Cow saveCow(@RequestBody Cow cow) {
        return cowRepository.save(cow);
    }

    @PutMapping("/update/{id}")
    public Cow updateCow(@RequestBody Cow cow, @PathVariable Long id) {
        return cowRepository.findById(id)
                .map(newCow -> {
                    newCow.setName(cow.getName());
                    newCow.setColor(cow.getColor());
                    newCow.setAge(cow.getAge());
                    return cowRepository.save(newCow);
                }).orElseGet(() -> {
                    cow.setId(id);
                    return cowRepository.save(cow);
                });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCowById(@PathVariable Long id) {
        cowRepository.deleteById(id);
    }

    @GetMapping("/color/{color}")
    public List<Cow> findCowsByColor(@PathVariable String color) {
        return cowRepository.findCowsByColor(color);
    }

    @GetMapping("/jpql/{color}")
    public List<Cow> findByColor(@PathVariable String color) {
        return cowRepository.findCowByColorJPQL(color);
    }

    @GetMapping("/native/{age}")
    public List<Cow> findA(@PathVariable int age) {
        return cowRepository.aaa(age);
    }

    @GetMapping("/joinclass")
    public List<CowHorseJoin> findCowHorse() {
        return cowRepository.findHorseCowJoin();
    }
}
