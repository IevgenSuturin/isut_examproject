package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Owner;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Owner> getAll(){
        return ownerRepository.findAll();
    }

    @Transactional
    @PostMapping(consumes = {"application/json"})
    public long addOwner(@RequestBody Owner owner) {
        return ownerRepository.create(owner);
    }
}
