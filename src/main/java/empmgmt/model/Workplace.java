package empmgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Workplace {

	@JsonIgnore
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "workplaces")
	private Set<Employee> employees = new HashSet<>();

	Workplace() {
	}

	public Workplace(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
}
