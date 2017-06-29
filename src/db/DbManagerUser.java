package db;

import java.sql.Time;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DbManagerUser extends DbManager {

	public DbManagerUser() {

	}
/*
	public void addLevelUser(LevelUsersDb ulDb) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.factory.openSession();
			tx = session.beginTransaction();
			session.save(ulDb);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}
*/

	public void addUser(UserDb userDb) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.factory.openSession();
			tx = session.beginTransaction();
			session.save(userDb);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void deleteUser(int userId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDb user = session.get(UserDb.class, userId);
			session.delete(user);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		}
		finally{
			session.close();
		}

	}
	public void updateFirstName(int userId,String name){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx=session.beginTransaction();
			UserDb user = session.get(UserDb.class, userId);
			user.setFirstName(name);
			session.update(user);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}



	}


	public void updateSteps(int userId,int steps){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx=session.beginTransaction();
			LevelUsersDb user = session.get(LevelUsersDb.class, userId);
			user.setSteps(steps);
			session.update(user);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	public void updateTime(int userId,Time time){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx=session.beginTransaction();
			LevelUsersDb user = session.get(LevelUsersDb.class, userId);
			user.setTime(time);
			session.update(user);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	public void updateLastName(int userId,String name){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx=session.beginTransaction();
			UserDb user = session.get(UserDb.class, userId);
			user.setLastName(name);
			session.update(user);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}


	}


	public void printUsers() {
		Session session = factory.openSession();
		try {
			Query<UserDb> query = session.createQuery("from Users");
			List<UserDb> list = query.list();
			for (UserDb db : list) {
				System.out.println(db);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

	}

	/////////////////////////////////////////
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

	public void theLevelUsers(LevelDb level,UserDb user,int steps,Time time){
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			LevelUsersDb lu = new LevelUsersDb(user.getUserId(), level.getLevelName());
			lu.setSteps(steps);
			lu.setTime(time);
			//user.getLevels().set(0, lu);
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
