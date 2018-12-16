package ua.skillsup.examproject.isut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.controller.dto.*;
import ua.skillsup.examproject.isut.dao.*;
import ua.skillsup.examproject.isut.dao.entity.*;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public long createItem(ItemDto itemDto, Long ownerId) throws NotEnoughDataToProcessTransaction {
       Item item = new Item(itemDto);
       long itemId = itemRepository.create(item);
       long count = item.getCount();
       item.setCount(0);
       createTransaction(itemId, ownerId, count);
       return itemId;
    }

    @Transactional
    @Override
    public boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction
    {
        boolean result = false;
        for (ItemDto itemDto:itemDtoList) {
            Item item = itemRepository.getOne(itemDto.getId());
            if (item == null){
                continue;
            }
            result = true;
            createTransaction(item.getId(), ownerId, (-1)*itemDto.getCount());
        }
        return result;
    }

    @Transactional
    @Override
    public boolean deleteItem(Long id) {
        Item item = itemRepository.getOne(id);
        if(item !=null ) {
           return itemRepository.delete(item);
        }
        return false;
    }

    @Transactional
    @Override
    public Iterable<OwnerDto> getAllOwners(){
        List<OwnerDto> result = new ArrayList<>();
        for (Owner owner: ownerRepository.findAll()) {
            result.add(new OwnerDto(owner));
        }
        return result;
    }

    @Transactional
    @Override
    public Iterable<OwnerDto> getActiveOwners() {
        List<OwnerDto> result = new ArrayList<>();
        for (Owner owner:accountRepository.getAllActiveOwners()) {
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
    public long createOwner(Owner owner) {
      return ownerRepository.create(owner);
    }

    @Transactional
    @Override
    public boolean deleteOwner(Long id) {
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
    public long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction {
        Owner owner = ownerRepository.getOne(ownerId);
        if ( owner == null ){
            throw new NotEnoughDataToProcessTransaction("Owner not found!");
        }
        Item item  = itemRepository.getOne(itemId);
        if ( item == null ){
            throw new NotEnoughDataToProcessTransaction("Item not found!");
        }

        Account account = accountRepository.getAccountByOwnerAndItem(owner, item);
        if(account == null){
            if (count>=0) {
                account = new Account(item, owner, 0);
                account.setId(accountRepository.create(account));
            }
            if(count<0) {
               throw new NotEnoughDataToProcessTransaction("No account for credit transaction!");
            }
        }

        if(account.getCount()+count<0){
              throw new NotEnoughDataToProcessTransaction("The amount of account for credit transaction les than transaction amount!");
        }

        if(item.getCount()+count<0){
             throw new NotEnoughDataToProcessTransaction("The amount of item for credit transaction les than transaction amount!");
        }

        account.setCount(account.getCount()+count);
        item.setCount(item.getCount()+count);

        accountRepository.update(account);
        itemRepository.update(item);

        return transRepository.create(new Transaction(item, owner, count));
    }

    @Transactional
    @Override
    public Iterable<AccDto> getAllAccounts() {
        List<AccDto> result = new ArrayList<>();
        for (Account account:accountRepository.findAll()) {
            result.add(new AccDto(account));
        }
        return result;
    }

    @Transactional
    @Override
    public Iterable<TransInfoDto> getStatisticForPeriod(short periodKind) {
        LocalDate start;
        List<TransInfoDto> result = new ArrayList<>();
        switch (periodKind) {
            case 0:
                start = LocalDate.now().minusDays(1);
                break;
            case 1:
                start = LocalDate.now().minusMonths(1);
                break;
            default:
                return null;
        }
        result.add(transRepository.getStatisticForPeriod(start, true));
        result.add(transRepository.getStatisticForPeriod(start, false));
        return result;
    }
}
