package yrojha.JavaStudHibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import yrojha.JavaStudHibernate.table.Address;

public class AddressDao {
	
	final static Logger logger = Logger.getLogger(AddressDao.class);
	
	public static void main(String[] args) {
		insert();
	}

	private static void insert() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Address address = new Address("Kathmandu", "Bagmati", "0977", "Nepal");
		Long id = (Long) session.save(address);

		session.getTransaction().commit();
		session.close();
		logger.info("Address is inserted.. Id: " + id);
	}
}
