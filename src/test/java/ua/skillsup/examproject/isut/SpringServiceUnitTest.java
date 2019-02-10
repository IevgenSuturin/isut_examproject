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
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.impl.AccountRepsitoryImpl;
import ua.skillsup.examproject.isut.dao.impl.ItemRepositoryImpl;
import ua.skillsup.examproject.isut.dao.impl.OwnerRepositoryImpl;
import ua.skillsup.examproject.isut.dao.impl.TransRepositoryImpl;
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

    @Before
    public void setUpMockedItemRepository(){
        Item item = new Item("Phone Samsung", "Samsung", 20);

        Mockito.when(mockedItemRepository.findByTitle("Phone Samsung")).thenReturn(Optional.of(new ItemDto(item)));
    }

    @Autowired
    private ChangeDataService changeDataService;

    @Autowired
    private GetInformationService getInformationService;

    @MockBean
    private ItemRepository mockedItemRepository;

    @Test
    public void whenValidName_thenItemShouldBeFound(){
        //given
        String title = "Phone Samsung";
        Optional<ItemDto> found = getInformationService.findByTitle(title);

        //than
        assertThat(found.get().getTitle()).isEqualTo(title);
    }

}
