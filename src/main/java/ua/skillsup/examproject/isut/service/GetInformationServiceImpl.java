package ua.skillsup.examproject.isut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.controller.dto.*;
import ua.skillsup.examproject.isut.dao.*;
import ua.skillsup.examproject.isut.dao.entity.*;
import ua.skillsup.examproject.isut.dao.support.PeriodType;
import ua.skillsup.examproject.isut.dao.support.TransTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetInformationServiceImpl implements GetInformationService {
    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final TransRepository transRepository;
    private final AccountRepository accountRepository;

    public GetInformationServiceImpl(ItemRepository itemRepository, OwnerRepository ownerRepository,
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
    public Iterable<AccDto> getAllAccounts() {
        List<AccDto> result = new ArrayList<>();
        for (Account account:accountRepository.findAll()) {
            result.add(new AccDto(account));
        }
        return result;
    }

    @Transactional
    @Override
    public Iterable<TransInfoDto> getStatisticForPeriod(PeriodType periodKind) {
        LocalDate start;
        List<TransInfoDto> result = new ArrayList<>();
        switch (periodKind) {
            case DAY:
                start = LocalDate.now().minusDays(1);
                break;
            case WEEK:
                start = LocalDate.now().minusWeeks(1);
                break;
            case MONTH:
                start = LocalDate.now().minusMonths(1);
                break;
            case YEAR:
                start = LocalDate.now().minusYears(1);
                break;
            default:
                return null;
        }
        result.add(transRepository.getStatisticForPeriod(start, TransTypes.STOREDTRANSACTIONS));
        result.add(transRepository.getStatisticForPeriod(start, TransTypes.WITHDRAWEDTRANSACTIONS));
        return result;
    }

    @Transactional
    @Override
    public Iterable<OwnerWithTotalPriceDto> getMostActiveOwners() {
        return accountRepository.getMostActiveOwners();
    }

}
