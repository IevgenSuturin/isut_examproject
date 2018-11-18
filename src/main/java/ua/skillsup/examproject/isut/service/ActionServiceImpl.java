package ua.skillsup.examproject.isut.service;

import org.springframework.stereotype.Service;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

@Service
public class ActionServiceImpl implements ActionService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;

    public ActionServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Iterable<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}
