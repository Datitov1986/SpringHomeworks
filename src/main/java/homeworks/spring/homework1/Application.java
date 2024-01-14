package homeworks.spring.homework1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		StudentRepository sudRepo = new StudentRepository();
		System.out.println(sudRepo.getAll());
		System.out.println(sudRepo.getById(2));
	}

}
