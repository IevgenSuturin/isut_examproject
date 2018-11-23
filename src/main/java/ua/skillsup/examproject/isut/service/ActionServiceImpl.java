package ua.skillsup.examproject.isut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TrTypeRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.TransTypes;

@Service
public class ActionServiceImpl implements ActionService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final TrTypeRepository trTypeRepository;

    @Autowired
    public ActionServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository, TrTypeRepository trTypeRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
        this.trTypeRepository = trTypeRepository;
    }

    @Override
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional
    @Override
    public long createItem(Item item) {
        return itemRepository.create(item);
    }

    @Override
    public Iterable<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Iterable<TransTypes> getAllTrTypes() { return trTypeRepository.getAll(); }

    @Transactional
    @Override
    public long createTrType(TransTypes transTypes) {
        return trTypeRepository.createTrType(transTypes);
    }

    @Transactional
    @Override
    public boolean addTransaction(long itemId, long ownerId, int count) {
        return false;
    }

    @Transactional
    @Override
    public boolean deleteItem(long id) {
        return itemRepository.delete(id);
    }

}
