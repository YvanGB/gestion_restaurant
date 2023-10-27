package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.Commande;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CommandeDaoImpl implements IDao<Commande>{
    @Override
    public void create(Commande entity) throws DAOException, RMSDBException {
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
    public Commande read(int id) throws DAOException, RMSDBException {
        Commande commande = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            commande = session.find(Commande.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return commande;
    }

    @Override
    public List<Commande> list() throws DAOException, RMSDBException {
        List<Commande> commandes = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_commande");
            commandes = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return commandes;

    }

    @Override
    public void update(Commande entity) throws DAOException, RMSDBException {
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

            Commande commande = read(id);
            if(commande != null) {
                session.delete(commande);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
    }
    }
