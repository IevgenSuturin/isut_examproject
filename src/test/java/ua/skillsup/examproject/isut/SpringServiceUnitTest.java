package ua.skillsup.examproject.isut;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.impl.AccountRepsitoryImpl;
import ua.skillsup.examproject.isut.dao.impl.ItemRepositoryImpl;
import ua.skillsup.examproject.isut.dao.impl.OwnerRepositoryImpl;
import ua.skillsup.examproject.isut.dao.impl.TransRepositoryImpl;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ChangeDataService;
import ua.skillsup.examproject.isut.service.ChangeDataServiceImpl;
import ua.skillsup.examproject.isut.service.GetInformationService;
import ua.skillsup.examproject.isut.service.GetInformationServiceImpl;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = "classpath:db/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:db/drop_schema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SpringServiceUnitTest {

    @TestConfiguration
    static class SpringDataUnitTestContextConfiguration{

        @Autowired
        EntityManager entityManager;

        @Bean
        public OwnerRepository ownerRepository(){
            return new OwnerRepositoryImpl(entityManager);
        }

        @Bean
        public TransRepository transRepository(){
            return new TransRepositoryImpl(entityManager);
        }

        @Bean
        public AccountRepository accountRepository(){
            return  new AccountRepsitoryImpl(entityManager);
        }

        @Bean
        public ItemRepository itemRepository(){
            return new ItemRepositoryImpl(entityManager);
        }

        @Bean
        public ChangeDataService changeDataService(){
            return new ChangeDataServiceImpl(itemRepository(), ownerRepository(), transRepository(), accountRepository());
        }

        @Bean
        public GetInformationService getInformationService(){
            return new GetInformationServiceImpl(itemRepository(), ownerRepository(), transRepository(), accountRepository());
        }

    }

    @Autowired
    private ChangeDataService changeDataService;

    @Autowired
    private GetInformationService informationService;

    @Test
    public void whenValidName_thenItemShouldBeFound() throws NotEnoughDataToProcessTransaction {
        //given
        Owner owner1 = new Owner("John", "Smith", "Gaget International");
        Item item1 = new Item("TV Samsung", "Samsung", 20);
        changeDataService.createOwner(owner1);
        changeDataService.createItem(new ItemDto(item1), owner1.getId());

        Optional<ItemDto> found = informationService.findItemByTitle(item1.getTitle());

        //than
        assertThat(found.get().getTitle()).isEqualTo(item1.getTitle());
    }

    @Test
    public void whenCreateOwner_thenReadIt(){
        //given
        Owner owner1 = new Owner("John", "Smith", "Gaget International");
        changeDataService.createOwner(owner1);

        //when
        Optional<OwnerDto> found = informationService.findOwnerByByFirstName(owner1.getFirst_name());

        //than
        assertThat(found.get().getFname()).isEqualTo(owner1.getFirst_name());
    }
}
