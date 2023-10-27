package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.Paiement;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaiementDaoImpl implements IDao<Paiement>{

    @Override
    public void create(Paiement entity) throws DAOException, RMSDBException {
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
    public Paiement read(int id) throws DAOException, RMSDBException {
        Paiement paiement = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            paiement = session.find(Paiement.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return paiement;
    }

    @Override
    public List<Paiement> list() throws DAOException, RMSDBException {
        List<Paiement> paiements = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_paiement");
            paiements = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return paiements;
    }

    @Override
    public void update(Paiement entity) throws DAOException, RMSDBException {
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

            Paiement paiement = read(id);
            if(paiement != null) {
                session.delete(paiement);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
    }
}
