package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.service.ActionService;

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
}
