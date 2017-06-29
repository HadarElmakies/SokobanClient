package db;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DbManagerLevel extends DbManager {

public DbManagerLevel() {
	super();
}
	public void addLevel(db.LevelDb level) {
		Session session = null;
		Transaction tx = null;
		try {
			session =this.factory.openSession();
			tx = session.beginTransaction();
			session.save(level);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void deleteLevel(String levelName) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			LevelDb levelDb = session.get(LevelDb.class, levelName);
			session.delete(levelDb);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

	public void updateName(String name){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();

		LevelDb levelDb = session.get(LevelDb.class, name);
		levelDb.setLevelName(name);
		session.update(levelDb);
		tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}


	}


}

/*
 * Session session =factory.openSession(); Transaction tx= null; try{
 * tx=session.beginTransaction(); LevelDb levelDb =
 * session.get(LevelDb.class,levelId); session.delete(levelDb); tx.commit(); }
 * catch (HibernateException ex){ if(tx!=null) tx.rollback(); } finally{
 * session.close(); }
 *
 *
 */
