package yrojha.JavaStudHibernate;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import yrojha.JavaStudHibernate.table.Address;
import yrojha.JavaStudHibernate.table.Department;
import yrojha.JavaStudHibernate.table.Employee;
import yrojha.JavaStudHibernate.table.PhoneNo;

public class EmployeeDao {

	public static void main(String[] args) {
		insertEmployeeAddressPhoneDept();
	}
	private static void insertEmployeeAddressPhoneDept() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Employee employee = new Employee("Bibek", "Shrestha", new Date(System.currentTimeMillis()));
		
		Address address = new Address("Kathmandu", "Bagmati", "0977", "Nepal");
		session.save(address);
		
		Department department = new Department("IT");
		session.save(department);
		
		PhoneNo phoneNo1 = new PhoneNo("977-9841665543");
		session.save(phoneNo1);
		phoneNo1.setEmployee(employee);
		
		PhoneNo phoneNo2 = new PhoneNo("977-9841431122");
		session.save(phoneNo2);
		phoneNo2.setEmployee(employee);
		
		employee.setAddress(address);
		employee.setDepartments(Arrays.asList(department));
		employee.setPhoneNos(Arrays.asList(phoneNo1, phoneNo2));
		
		session.saveOrUpdate(employee);

		session.getTransaction().commit();
		session.close();
	}

	private static Employee oneToOneMapping() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Address address = (Address) session.get(Address.class, 1L);
		Employee emp = new Employee("Anjan", "Pant", new Date(System.currentTimeMillis()), address);

		session.save(emp);

		session.getTransaction().commit();
		session.close();
		return emp;
	}

	private static Employee getEmp() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", 13L));

		Employee emp = (Employee) criteria.uniqueResult();

		System.out.println(emp);
		return emp;
	}

	private static List<Employee> getAllEmployees() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> empList = (List<Employee>) criteria.list();

		return empList;
	}

	private static void insert() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Employee emp = new Employee("Rajan", "Shrestha", new Date(System.currentTimeMillis()));
		Long id = (Long) session.save(emp);

		session.getTransaction().commit();

		session.close();

		System.out.println("Data Inserted. Id: " + id);
	}

	private static Employee get() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session session = sessionFactory.openSession();

		Employee emp = (Employee) session.get(Employee.class, 11L);

		System.out.println(emp);

		return emp;
	}

	public static void update() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Employee emp = (Employee) session.get(Employee.class, 11L);
		emp.setLastname("Joshi");

		session.update(emp);

		session.getTransaction().commit();
		session.close();
		System.out.println("Updated Employee Info: " + emp);
	}

	private static void delete() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Employee employee = (Employee) session.get(Employee.class, 11L);
		session.delete(employee);

		session.getTransaction().commit();
		session.close();
	}

}
