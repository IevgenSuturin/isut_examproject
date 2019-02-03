package ua.skillsup.examproject.isut;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.service.ChangeDataService;
import ua.skillsup.examproject.isut.service.GetInformationService;

@ImportResource("classpath:/spring/db-context.xml")
@SpringBootApplication
public class ExamProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext= SpringApplication.run(ExamProjectApplication.class, args);
        GetInformationService infoService = applicationContext.getBean(GetInformationService.class);
        ChangeDataService dataService = applicationContext.getBean(ChangeDataService.class);

        System.out.println(infoService.getAllItems());
        System.out.println(infoService.getAllOwners());
    }

    @Bean
    public CommandLineRunner initItemData (ChangeDataService dataService){
        return args -> {
          Owner owner1 = new Owner("John", "Smith", "Gaget International");
            dataService.createOwner(owner1);
          Owner owner2 = new Owner("Eric", "Smith", "Gaget International");
            dataService.createOwner(owner2);
          Item item1 = new Item("TV Samsung", "Samsung", 20);
            dataService.createItem(new ItemDto(item1), owner1.getId());
          Item item2 = new Item("TV LG", "LG", 15);
            dataService.createItem(new ItemDto(item2), owner2.getId());
         // service.createTransaction(item1.getId(), owner1.getId(), new Long(10));
         // service.createTransaction(item2.getId(), owner1.getId(), new Long(20));
        };
    }

}
