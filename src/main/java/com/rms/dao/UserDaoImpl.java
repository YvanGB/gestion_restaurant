package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

    public class UserDaoImpl implements IDao<User> {
        @Override
        public void create(User entity) throws DAOException, RMSDBException {
            // TODO Auto-generated method stub
            try {
                Session session = HibernateConnection.getInstance().getSession();

                Transaction transaction = session.beginTransaction();

                session.persist(entity);

                transaction.commit();
            } catch (Exception e) {
                throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
            }

        }

        @Override
        public User read(int id) throws DAOException, RMSDBException {
            // TODO Auto-generated method stub
            User user = null;
            try {
                Session session = HibernateConnection.getInstance().getSession();
                user = session.find(User.class, id);

            } catch (Exception e) {
                throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
            }
            return user;

        }

        @Override
        public List<User> list() throws DAOException, RMSDBException {
            List<User> users = new ArrayList<>();
            try {
                Session session = HibernateConnection.getInstance().getSession();

                Query query = session.createQuery("From t_user");
                users = query.getResultList();
                //HibernateConnection.getInstance().closeSession();
            } catch (Exception e) {
                throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
            }
            return users;

        }

        @Override
        public void update(User entity) throws DAOException, RMSDBException {
            try {
                Session session = HibernateConnection.getInstance().getSession();

                Transaction transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();

            } catch (Exception e) {
                throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
            }

        }

        @Override
        public void delete(int id) throws DAOException, RMSDBException {
            try {
                Session session = HibernateConnection.getInstance().getSession();

                Transaction transaction = session.beginTransaction();

                User user = read(id);
                if (user != null) {
                    session.delete(user);
                }

                transaction.commit();
            } catch (Exception e) {
                throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
            }

        }
    }