package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.ChefCuisinier;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ChefCuisinierDaoImpl implements IDao<ChefCuisinier>{

    @Override
    public void create(ChefCuisinier entity) throws DAOException, RMSDBException {
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
    public ChefCuisinier read(int id) throws DAOException, RMSDBException {
        ChefCuisinier chefCuisinier = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            chefCuisinier = session.find(ChefCuisinier.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return chefCuisinier;

    }

    @Override
    public List<ChefCuisinier> list() throws DAOException, RMSDBException {
        List<ChefCuisinier> chefCuisiniers = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_chefCuisinier");
            chefCuisiniers = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return chefCuisiniers;
    }

    @Override
    public void update(ChefCuisinier entity) throws DAOException, RMSDBException {
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

            ChefCuisinier chefCuisinier = read(id);
            if(chefCuisinier != null) {
                session.delete(chefCuisinier);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
    }
    }
