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

          service.createItem(new ItemDto(new Item("TV Samsung", "Samsung")), owner1.getId());
          service.createItem(new ItemDto(new Item("TV LG", "LG")), owner2.getId());

        };
    }

}
