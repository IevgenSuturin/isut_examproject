package ua.skillsup.examproject.isut;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import ua.skillsup.examproject.isut.dao.entity.TransTypes;
import ua.skillsup.examproject.isut.service.ActionService;

@ImportResource("classpath:/spring/db-context.xml")
@SpringBootApplication
public class ExamProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext= SpringApplication.run(ExamProjectApplication.class, args);
        ActionService service = applicationContext.getBean(ActionService.class);

        System.out.println(service.getAllItems());
        System.out.println(service.getAllOwners());
        System.out.println(service.getAllTrTypes());
    }

    @Bean
    public CommandLineRunner initData (ActionService service){
        return args -> {
          service.createTrType(new TransTypes("store", "description of store transaction"));
          service.createTrType(new TransTypes("withdrew", "description of withdrew transaction"));
        };
    }
}
