package ua.skillsup.examproject.isut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.controller.dto.TransDto;
import ua.skillsup.examproject.isut.dao.*;
import ua.skillsup.examproject.isut.dao.entity.*;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.exceptions.NotImplementedDataAccessMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActionServiceImpl implements ActionService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final TransRepository transRepository;
    private final AccountRepository accountRepository;

    public ActionServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository,
                             TransRepository transRepository, AccountRepository accountRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
        this.transRepository = transRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public Iterable<ItemDto> getAllItems()  {
        List<ItemDto> result = new ArrayList<>();
        for (Item item:itemRepository.findAll()) {
            result.add(new ItemDto(item));
        }
        return result;
    }

    @Transactional
    @Override
    public long createItem(Item item) {
       return itemRepository.create(item);
    }

    @Transactional
    @Override
    public Iterable<OwnerDto> getAllOwners() {
        List<OwnerDto> result = new ArrayList<>();
        for (Owner owner: ownerRepository.findAll()) {
            result.add(new OwnerDto(owner));
        }
        return result;
    }

    @Transactional
    @Override
    public Iterable<TransDto> getAllTransactions() {
        List<TransDto> result = new ArrayList<>();
        for (Transaction trans:transRepository.findAll()) {
            result.add(new TransDto(trans));
        }
        return result;
    }

    @Transactional
    @Override
    public boolean deleteItem(long id) {
        Item item = itemRepository.getOne(id);
        return itemRepository.delete(item);
    }

    @Transactional
    @Override
    public long createOwner(Owner owner) {
      return ownerRepository.create(owner);
    }

    @Transactional
    @Override
    public boolean deleteOwner(long id) {
        Owner owner = ownerRepository.getOne(id);
        if(owner != null && !accountRepository.isActiveOwnerAccountExists(owner))
        {
            transRepository.deleteOwnerTransactions(owner);
            accountRepository.deleteOwnerAccounts(owner);
            return ownerRepository.delete(owner);
        }
       return false;
    }

    @Transactional
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

        Account account = accountRepository.getAccountByOwnerAndItem(owner, item);
        if(account == null){
            if (transaction.getCount()>=0) {
                account = new Account(item, owner, 0);
                account.setId(accountRepository.create(account));
            }
            if(transaction.getCount()<0) {
               throw new NotEnoughDataToProcessTransaction("No account for credit transaction!");
            }
        }

        if(account.getCount()+transaction.getCount()<0){
              throw new NotEnoughDataToProcessTransaction("The amount of account for credit transaction les than transaction amount!");
        }

        if(item.getCount()+item.getCount()<0){
             throw new NotEnoughDataToProcessTransaction("The amount of item for credit transaction les than transaction amount!");
        }

        account.setCount(account.getCount()+transaction.getCount());
        item.setCount(item.getCount()+transaction.getCount());

        accountRepository.update(account);
        itemRepository.update(item);

        return transRepository.create(transaction);
    }
}
