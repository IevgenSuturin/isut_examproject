package ua.skillsup.unit_test;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ChangeDataService;
import ua.skillsup.examproject.isut.service.ChangeDataServiceImpl;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeDataServiceTest {
    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransRepository transRepository;

    private ChangeDataService service;

    @BeforeEach
    public void Init(){
        MockitoAnnotations.initMocks(this);
        this.service = new ChangeDataServiceImpl(itemRepository, ownerRepository, transRepository, accountRepository);
    }

    @Test
    @DisplayName("Owner creation")
    public void ownerCreation(){
        final String ownerFirstName = "FirstName";
        final String ownerLastName = "LastName";
        final String ownerCompanyName = "CompanyName";
        final Long ownerId = new Long(100);

        Owner ownerToReturnFromRepository = new Owner(ownerFirstName, ownerLastName, ownerCompanyName);
        when(ownerRepository.create(Mockito.any(Owner.class))).thenReturn(ownerId);

        Long found = service.createOwner(ownerToReturnFromRepository);

        assertThat(ownerId).isEqualTo(found);
    }

    @Test
    @DisplayName("Item creation")
    public void itemCreation(){
        final Long itemId = new Long(100);
        final Long ownerId = new Long(101);

        Item item = new Item("Test item title", "Test item description", 100);
        ItemDto itemDto = new ItemDto(item);

        when(itemRepository.create(Mockito.any(Item.class))).thenReturn(itemId);

        Long found;
        try {
            found = service.createItem(itemDto, ownerId);
        } catch (NotEnoughDataToProcessTransaction e){
          found = Long.valueOf(0);
        }

        assertThat(ownerId).isEqualTo(found);
    }

}
