package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.service.ActionService;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final ActionService service;

    @Autowired
    public OwnerController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Owner> getAllOwners(){
        return service.getAllOwners();
    }

    @Transactional
    @PostMapping(consumes = {"application/json"})
    public long addOwner(@RequestBody Owner owner) {
        return service.createOwner(owner);
    }

    @Transactional
    @DeleteMapping(consumes = {"application/json"})
    public boolean deleteOwner(@PathVariable long ownerid){
        return service.deleteOwner(ownerid);
    }
}
