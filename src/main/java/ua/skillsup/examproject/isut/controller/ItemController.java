package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.service.GetInformationService;

@RestController
@RequestMapping("items")
public class ItemController {
    private final GetInformationService service;

    @Autowired
    public ItemController(GetInformationService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<ItemDto> getAllItems() {return service.getAllItems();}
}
