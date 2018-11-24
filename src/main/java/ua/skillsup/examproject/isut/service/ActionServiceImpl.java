package ua.skillsup.examproject.isut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.dao.*;
import ua.skillsup.examproject.isut.dao.entity.*;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.exceptions.NotImplementedDataAccessMethod;

@Service
public class ActionServiceImpl implements ActionService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final TrTypeRepository trTypeRepository;
    private final TransRepository transRepository;
    private final AccountRepository accountRepository;

    public ActionServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository,
                             TrTypeRepository trTypeRepository, TransRepository transRepository,
                             AccountRepository accountRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
        this.trTypeRepository = trTypeRepository;
        this.transRepository = transRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Iterable<Item> getAllItems()  {
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
    public Iterable<TransTypes> getAllTrTypes() { return trTypeRepository.findAll(); }

    @Transactional
    @Override
    public long createTrType(TransTypes transTypes) {
       return trTypeRepository.create(transTypes);
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

    @Override
    public long createOwner(Owner owner) {
      return ownerRepository.create(owner);
    }

    @Override
    public boolean deleteOwner(long id) {
       return ownerRepository.delete(id);
    }

    @Override
    public long createTransaction(Transaction transaction) throws NotEnoughDataToProcessTransaction {
        Owner owner = ownerRepository.getOne(transaction.getOwner().getId());
        if ( owner == null ){
            throw new NotEnoughDataToProcessTransaction("Owner not found!");
        }
        Item item  = itemRepository.getOne(transaction.getItem().getId());
        if ( item == null ){
            throw new NotEnoughDataToProcessTransaction("Item not found!");
        }
        TransTypes transTypes = trTypeRepository.getOne(transaction.getTrtype().getId());
        if ( item == null ){
            throw new NotEnoughDataToProcessTransaction("Transaction type is incorrect!");
        }
        if( transaction.getCount() <= 0){
            throw new NotEnoughDataToProcessTransaction("Transaction count should be positive!");
        }

        Account account = accountRepository.getAccountByOwnerAndItem(owner.getId(), item.getId());
        if(account == null && transTypes.getId() == 1){
            account = new Account(item, owner, 0);
            account.setId(accountRepository.create(account));
        }

        if(account == null && transTypes.getId() == 2){
            throw new NotEnoughDataToProcessTransaction("There is no account for credit transaction!");
        }

        if(transTypes.getId() == 1){
            account.setCount(account.getCount()+transaction.getCount());
            item.setCount(item.getCount()+transaction.getCount());
        }

        if(transTypes.getId() == 2){
            if(account.getCount()<transaction.getCount()){
                throw new NotEnoughDataToProcessTransaction("The amount of account for credit transaction les than transaction amount!");
            }
            if(item.getCount()<item.getCount()){
                throw new NotEnoughDataToProcessTransaction("The amount of item for credit transaction les than transaction amount!");
            }
            account.setCount(account.getCount()-transaction.getCount());
            item.setCount(item.getCount()-transaction.getCount());
        }
        accountRepository.update(account);
        itemRepository.update(item);

        return transRepository.create(transaction);
    }
}
