package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DbManagerLevelUsers extends DbManager {

	public DbManagerLevelUsers() {
		super();
	}

	public void addUser(LevelUsersDb lu) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.factory.openSession();
			tx = session.beginTransaction();
			session.save(lu);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}


	public void theLevelUsers(LevelDb level,UserDb user){
		Session session = null;
		Transaction tx = null;
		try {
			session = this.factory.openSession();
			tx = session.beginTransaction();
			LevelUsersDb lu = new LevelUsersDb(user.getUserId(), level.getLevelName());
			session.save(lu);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}


	}


}
