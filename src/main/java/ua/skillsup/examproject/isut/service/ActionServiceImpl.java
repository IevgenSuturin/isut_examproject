package ua.skillsup.examproject.isut.service;

import org.springframework.stereotype.Service;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {
    private final ItemRepository itemRepository;

    public ActionServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
