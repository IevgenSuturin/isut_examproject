package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.service.ActionService;
import ua.skillsup.examproject.isut.controller.output.OPItem;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ActionService service;

    @Autowired
    public ItemController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<OPItem> getAllItems() {return service.getAllItems();}

    @PostMapping(consumes = {"application/json"})
    public long addItem(@RequestBody Item item) {
        return service.createItem(item);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "itemid/{itemid}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemid)
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
