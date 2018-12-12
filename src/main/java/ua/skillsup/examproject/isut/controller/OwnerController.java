package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
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
    public Iterable<OwnerDto> getActiveOwners(){
        return service.getAllOwners();
    }

    @PostMapping(consumes = {"application/json"})
    public long addOwner(@RequestBody Owner owner) {
        return service.createOwner(owner);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{ownerid}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long ownerid){
        if(service.deleteOwner(ownerid)){
            return new ResponseEntity<String>( "Owner id:"+ownerid+" was deleted", HttpStatus.ACCEPTED);

        }else{
            return new ResponseEntity<String>( "Owner id:"+ownerid+" was not deleted", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
