package ua.skillsup.examproject.isut;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ActionService;

@ImportResource("classpath:/spring/db-context.xml")
@SpringBootApplication
public class ExamProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext= SpringApplication.run(ExamProjectApplication.class, args);
        ActionService service = applicationContext.getBean(ActionService.class);

        System.out.println(service.getAllItems());
        System.out.println(service.getAllOwners());
    }

    @Bean
    public CommandLineRunner initItemData (ActionService service){
        return args -> {
          Owner owner1 = new Owner("John", "Smith", "Gaget International");
          service.createOwner(owner1);
          Owner owner2 = new Owner("Eric", "Smith", "Gaget International");
          service.createOwner(owner2);
          Item item1 = new Item("TV Samsung", "Samsung", 20);
          service.createItem(new ItemDto(item1), owner1.getId());
          Item item2 = new Item("TV LG", "LG", 15);
          service.createItem(new ItemDto(item2), owner2.getId());
         // service.createTransaction(item1.getId(), owner1.getId(), new Long(10));
         // service.createTransaction(item2.getId(), owner1.getId(), new Long(20));
        };
    }

}
