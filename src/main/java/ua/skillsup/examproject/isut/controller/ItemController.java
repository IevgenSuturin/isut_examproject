package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Item> getAll() {return itemRepository.findAll();}
}
