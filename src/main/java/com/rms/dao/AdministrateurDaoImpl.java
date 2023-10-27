package com.rms.dao;


import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.Administrateur;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdministrateurDaoImpl implements IDao<Administrateur>{
    @Override
    public void create(Administrateur entity) throws DAOException, RMSDBException {
        // TODO Auto-generated method stub
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
    public Administrateur read(int id) throws DAOException, RMSDBException {
        // TODO Auto-generated method stub
        Administrateur administrateur = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            administrateur = session.find(Administrateur.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return administrateur;

    }

    @Override
    public List<Administrateur> list() throws DAOException, RMSDBException {
        List<Administrateur> administrateurs = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_administrateur");
            administrateurs = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return administrateurs;

    }

    @Override
    public void update(Administrateur entity) throws DAOException, RMSDBException {
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

            Administrateur administrateur = read(id);
            if(administrateur != null) {
                session.delete(administrateur);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }

    }
}
