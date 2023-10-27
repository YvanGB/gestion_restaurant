package com.rms.dao;

import com.rms.db.HibernateConnection;
import com.rms.db.RMSDBException;
import com.rms.model.Recette;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecetteDaoImpl implements IDao<Recette>{

    @Override
    public void create(Recette entity) throws DAOException, RMSDBException {
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
    public Recette read(int id) throws DAOException, RMSDBException {
        Recette recette = null;
        try {
            Session session = HibernateConnection.getInstance().getSession();
            recette = session.find(Recette.class, id);

        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return recette;

    }

    @Override
    public List<Recette> list() throws DAOException, RMSDBException {
        List<Recette> recettes = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("From t_recette");
            recettes = query.getResultList();
            //HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return recettes;
    }

    public List<Recette> listByPeriod(LocalDate startDate, LocalDate endDate) throws DAOException, RMSDBException {
        List<Recette> recettes = new ArrayList<>();
        try {
            Session session = HibernateConnection.getInstance().getSession();

            Query query = session.createQuery("FROM t_recette r WHERE r.date >= :startDate AND r.date <= :endDate");
            query.setParameter("startDate", Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            query.setParameter("endDate", Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

            recettes = query.getResultList();
            // HibernateConnection.getInstance().closeSession();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
        return recettes;
    }

    @Override
    public void update(Recette entity) throws DAOException, RMSDBException {
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

            Recette recette = read(id);
            if(recette != null) {
                session.delete(recette);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
        }
    }
}
