package com.spring_sql.sql_work.controller;

import com.spring_sql.sql_work.model.Horse;
import com.spring_sql.sql_work.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/horse")
public class HorseController {
    @Autowired
    HorseRepository horseRepository;

    @GetMapping
    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    @GetMapping("/order")
    public List<Horse> findOnPage() {
        return horseRepository.findAll(PageRequest.of(0,2, Sort.by(Sort.Direction.ASC, "age"))).getContent();
    }

    @GetMapping("/custom/{color}")
    public List<Horse> search(@PathVariable String color) {
        return horseRepository.findAllByColorOrderByAge(color);
    }

    @GetMapping("/one/{id}")
    public Optional<Horse> getHorse(@PathVariable Long id) {
        return horseRepository.findById(id);
    }

    @PostMapping
    public Horse saveHorse(@RequestBody Horse horse) {
        return horseRepository.save(horse);
    }

    @PutMapping("/update/{id}")
    public Horse updateHorse(@RequestBody Horse horse, @PathVariable Long id) {
        return horseRepository.findById(id)
                .map(newHorse -> {
                    newHorse.setName(horse.getName());
                    newHorse.setColor(horse.getColor());
                    newHorse.setAge(horse.getAge());
                    return horseRepository.save(newHorse);
                }).orElseGet(() -> {
                    horse.setId(id);
                    return horseRepository.save(horse);
                });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHorse(@PathVariable Long id) {
        horseRepository.deleteById(id);
    }
}
