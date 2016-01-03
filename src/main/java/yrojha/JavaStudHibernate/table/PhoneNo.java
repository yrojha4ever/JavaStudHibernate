package yrojha.JavaStudHibernate.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone_no")
public class PhoneNo {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number")
	private String number;

	// 2. Many to One mapping. Many mobile no can be used by One Employee.
	// Required:ManyToOne is compulsory but OneToMany is not Compulsory.
	// If we have both that is called Bidirectional relation.
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public PhoneNo(){
		
	}

	public PhoneNo(String number) {
		this.number = number;
	}
	
	public PhoneNo(String number, Employee employee) {
		this.number = number;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ID :" + id + ", Number: " + number + ", employee_id: " + employee.getId();
	}

}
