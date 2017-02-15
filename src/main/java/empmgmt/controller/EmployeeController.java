package empmgmt.controller;

import empmgmt.EmployeeNotFoundException;
import empmgmt.model.Employee;
import empmgmt.model.Workplace;
import empmgmt.repository.EmployeeRepository;
import empmgmt.repository.WorkplaceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	private WorkplaceRepository workplaceRepository;

	public EmployeeController(EmployeeRepository employeeRepository, WorkplaceRepository workplaceRepository) {
		this.employeeRepository = employeeRepository;
		this.workplaceRepository = workplaceRepository;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Employee view(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Employee create(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phoneNumber", required = false) String phoneNumber) {

		Employee employee = new Employee(name, address, email, phoneNumber);
		employeeRepository.save(employee);
		return employee;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public Employee update(
			@PathVariable Long id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "address", required = false) String address,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phoneNumber", required = false) String phoneNumber) {

		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		employee.setName(name != null ? name : employee.getName());
		employee.setAddress(address != null ? address : employee.getAddress());
		employee.setEmail(email != null ? email : employee.getEmail());
		employee.setPhoneNumber(phoneNumber != null ? phoneNumber : employee.getPhoneNumber());
		employeeRepository.save(employee);
		return employee;
	}

	@RequestMapping(value = "/addWorkplace/{id}", method = RequestMethod.POST)
	public Employee addWorkplace(
			@PathVariable Long id,
			@RequestParam(name = "workplace") String workplaceName) {

		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		Workplace workplace = workplaceRepository.findByNameIgnoreCase(workplaceName).orElseGet(() -> workplaceRepository.save(new Workplace(workplaceName)));
		employee.getWorkplaces().add(workplace);
		return employeeRepository.save(employee);
	}
}
