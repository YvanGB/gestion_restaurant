package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.Restaurateur;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RestaurateurDaoImpl implements IDao<Restaurateur>{

    @Override
    public void create(Restaurateur entity) throws DAOException, RMSDBException {
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Transaction transaction	= session.beginTransaction();

            session.persist(entity);

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
        }
    }

    @Override
    public Restaurateur read(int id) throws DAOException, RMSDBException {
        Restaurateur restaurateur = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            restaurateur = session.find(Restaurateur.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return restaurateur;
    }

    @Override
    public List<Restaurateur> list() throws DAOException, RMSDBException {
        List<Restaurateur> restaurateurs = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_restaurateur");
            restaurateurs = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return restaurateurs;
    }

    @Override
    public void update(Restaurateur entity) throws DAOException, RMSDBException {
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Transaction transaction	= session.beginTransaction();
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

            Transaction transaction	= session.beginTransaction();

            Restaurateur restaurateur = read(id);
            if(restaurateur != null) {
                session.delete(restaurateur);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
    }
}
