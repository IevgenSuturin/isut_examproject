package ua.skillsup.examproject.isut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
}
