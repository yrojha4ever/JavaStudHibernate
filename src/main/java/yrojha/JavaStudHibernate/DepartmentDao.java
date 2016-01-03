package yrojha.JavaStudHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import yrojha.JavaStudHibernate.table.Department;

public class DepartmentDao {

	public static void main(String[] args) {
		insert();
	}

	private static void insert() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Department address = new Department("IT");
		Long id = (Long) session.save(address);

		session.getTransaction().commit();
		session.close();
		System.out.println("Department is inserted.. Id: " + id);
	}
}
