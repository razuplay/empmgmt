package empmgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Employee {

	@Id @GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	private String email;
	private String phoneNumber;

	@JsonIgnore
	@ManyToMany
	private Set<Workplace> workplaces = new HashSet<>();

	Employee() {
	}

	public Employee(String name, String address, String email, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Workplace> getWorkplaces() {
		return workplaces;
	}

	public String getWorkplacesString() {
		return workplaces.stream().map(Workplace::getName).collect(Collectors.joining(", "));
	}
}
