package empmgmt;

import empmgmt.model.Employee;
import empmgmt.model.Workplace;
import empmgmt.repository.EmployeeRepository;
import empmgmt.repository.WorkplaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner init(EmployeeRepository employeeRepository, WorkplaceRepository workplaceRepository) {
		return (args) -> {
			List<Workplace> workplaces = workplaceRepository.save(Arrays.asList(new Workplace("Office 1"), new Workplace("Office 2")));

			Employee e1 = new Employee("Jan Kowalski", "ul. Kwiatowa 1, 43-300 Bielsko-Biała", "jan.kowalski@email.com", "+48 555 123 123");
			e1.getWorkplaces().addAll(workplaces);
			employeeRepository.save(e1);

			Employee e2 = new Employee("Marian Nowak", "ul. Willowa 6, 43-300 Bielsko-Biała", "marian.nowak@email.com", "+48 555 456 456");
			e2.getWorkplaces().add(workplaces.get(0));
			employeeRepository.save(e2);
		};
	}
}
