package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerWithTotalPriceDto;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ActionService;

import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final ActionService service;

    @Autowired
    public OwnerController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<OwnerDto> getAllOwners(){
        return service.getAllOwners();
    }

    @GetMapping(produces = {"application/json"}, value = "active")
    public Iterable<OwnerDto> getActiveOwners(){
        return service.getActiveOwners();
    }

    @GetMapping(produces = {"application/json"}, value = "most-active")
    public Iterable<OwnerWithTotalPriceDto> getMostActiveOwners(){
        return service.getMostActiveOwners();
    }

    @PostMapping(consumes = {"application/json"})
    public long addOwner(@RequestBody Owner owner) {
        return service.createOwner(owner);
    }

    @PostMapping(consumes = {"application/json"}, value = "{ownerId}/items/add")
    public ResponseEntity<String> addItem(@RequestBody ItemDto itemDto, @PathVariable Long ownerId)
    {
        try{
            Long itemId = service.createItem(itemDto, ownerId);
            return new ResponseEntity<String>("Item id = "+ itemId.toString()+" added correctly.", HttpStatus.ACCEPTED);
        }catch (NotEnoughDataToProcessTransaction ex) {
            return new ResponseEntity<String>(ex.getMessage()+" Item was not added correctly.", HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(consumes = {"application/json"}, value = "{ownerId}/items/withdraw")
    public ResponseEntity<String> withdrawItem(@RequestBody List<ItemDto> itemDtoList, @PathVariable Long ownerId)
    {
        try {
            if (service.withdrawItems(itemDtoList, ownerId)) {
                return new ResponseEntity<String>("Item list was withdrawed for owner id=" + ownerId.toString(), HttpStatus.ACCEPTED);
            }
        } catch (NotEnoughDataToProcessTransaction ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<String>("Item list was not withdrawed for owner id="+ownerId.toString(), HttpStatus.NOT_ACCEPTABLE);
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
