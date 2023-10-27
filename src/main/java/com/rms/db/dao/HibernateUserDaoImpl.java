package com.rms.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.rms.model.User;
import jakarta.persistence.Query;

import com.rms.dao.IDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;


public class HibernateUserDaoImpl implements IDao<User> {
    public void create(User user) throws RMSDBException {
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Transaction transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();
        }catch (Exception e){
            throw new RMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public User read(int id) throws RMSDBException {
        User user = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            user = session.find(User.class, id);
        }catch (Exception e){
            throw new RMSDBException("ERROR:"+e.getClass()+":"+e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> list() throws RMSDBException {
        List<User> users = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = (Query) session.createQuery("From my_users");
            users = query.getResultList();
        }catch (Exception e){
            throw new RMSDBException("ERROR:"+e.getClass()+":"+e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) throws RMSDBException {
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Transaction transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        }catch (Exception e){
            throw new RMSDBException("ERROR:"+e.getClass()+":"+e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws RMSDBException {
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Transaction transaction = session.beginTransaction();

            User user = read(id);
            if(user != null) session.remove(user);

            transaction.commit();
        }catch (Exception e){
            throw new RMSDBException("ERROR:"+e.getClass()+":"+e.getMessage());
        }
    }
}
