package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ActionService;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ActionService service;

    @Autowired
    public ItemController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<ItemDto> getAllItems() {return service.getAllItems();}

    @PostMapping(consumes = {"application/json"}, value = "ownerid/{ownerId}")
    public ResponseEntity<String> addItem(@RequestBody ItemDto itemDto, @PathVariable long ownerId)
    {
        try{
            Long itemId = new Long(service.createItem(itemDto, ownerId));
            return new ResponseEntity<String>("Item id = "+ itemId.toString()+" added correctly.", HttpStatus.ACCEPTED);
        }catch (NotEnoughDataToProcessTransaction ex) {
            return new ResponseEntity<String>(ex.getMessage()+" Item was not added correctly.", HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(consumes = {"application/json"}, value = "withdraw/ownerid/{ownerId}")
    public ResponseEntity<String> withdrawItem(@RequestBody List<ItemDto> itemDtoList, @PathVariable long ownerId)
    {
        try {
            if (service.withdrawItems(itemDtoList, ownerId)) {
                return new ResponseEntity<String>("Item list was withdrawed for owner id=" + new Long(ownerId).toString(), HttpStatus.ACCEPTED);
            }
        } catch (NotEnoughDataToProcessTransaction ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<String>("Item list was not withdrawed for owner id="+new Long(ownerId).toString(), HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "itemid/{itemid}")
    public ResponseEntity<String> deleteItem(@PathVariable long itemid)
    {
        try {
            if (service.deleteItem(new Long(itemid))) {
                return new ResponseEntity<String>("Data deleted successfully", HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<String>( "Record was not deleted", HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<String>( ex.getMessage()+" Record was not deleted", HttpStatus.NOT_FOUND);
        }
    }
}
