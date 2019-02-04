package ua.skillsup.examproject.isut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Account;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

import java.util.List;

@Service
public class ChangeDataServiceImpl implements ChangeDataService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final TransRepository transRepository;
    private final AccountRepository accountRepository;

    public ChangeDataServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository,
                                 TransRepository transRepository, AccountRepository accountRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
        this.transRepository = transRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public Long createItem(ItemDto itemDto, Long ownerId) throws NotEnoughDataToProcessTransaction {
        Item item = new Item(itemDto);
        long itemId = itemRepository.create(item);
        long count = item.getCount();
        item.setCount(0);
        createTransaction(itemId, ownerId, count);
        return itemId;
    }

    @Transactional
    @Override
    public Boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction
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
    public Boolean deleteItem(Long id) {
        Item item = itemRepository.getOne(id);
        if(item !=null ) {
            return itemRepository.delete(item);
        }
        return false;
    }

    @Transactional
    @Override
    public Long createOwner(Owner owner) {
        return ownerRepository.create(owner);
    }

    @Transactional
    @Override
    public Boolean deleteOwner(Long id) {
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
    public Long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction {
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

        if(!account.isTransactionAvailable(count)){
            throw new NotEnoughDataToProcessTransaction("The amount of account for credit transaction less than transaction amount!");
        }

        if(!item.isTransactionAvailable(count)){
            throw new NotEnoughDataToProcessTransaction("The amount of item for credit transaction less than transaction amount!");
        }

        account.setCount(account.getCount()+count);
        item.setCount(item.getCount()+count);

        accountRepository.update(account);
        itemRepository.update(item);

        return transRepository.create(new Transaction(item, owner, count));
    }
}
