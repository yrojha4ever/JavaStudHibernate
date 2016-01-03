package yrojha.JavaStudHibernate.table;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "birth_date")
	private Date birthDate;

	// 1. One To One Mapping. Employee has a Address.
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	// Optional: 2. One To Many mapping.Employee have Many Mobile No.s
	// MappedBy: PhoneNo.java>employee Field.
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	private List<PhoneNo> phoneNos;

	// 4. Many To Many mapping. Employee can Have Many Department and One
	// Department Can have Many Employees. NOTE: Write Join Column Line In only
	// One Place. Either in Employee.class or Department.class.
	@ManyToMany
	@JoinTable(name = "employee_dept", joinColumns = { @JoinColumn(name = "emp_id") }, inverseJoinColumns = {
			@JoinColumn(name = "dept_id") })
	private List<Department> departments;

	public Employee() {

	}

	public Employee(String firstname, String lastname, Date birthdate) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthdate;
	}

	public Employee(String firstname, String lastname, Date birthdate, Address address) {
		this(firstname, lastname, birthdate);
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PhoneNo> getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(List<PhoneNo> phoneNos) {
		this.phoneNos = phoneNos;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "Id: " + id + ", firstname: " + firstname + ", lastname: " + lastname + ", birthDate: " + birthDate;
	}

}