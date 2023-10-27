package com.rms.dao;

import com.rms.db.RMSDBException;

import java.util.List;

public interface IDao <T>{
    public void create (T entity) throws DAOException, RMSDBException;
    public T read (int id) throws DAOException, RMSDBException;
    public List<T> list () throws DAOException, RMSDBException;
    public void update (T entity) throws DAOException, RMSDBException;
    public void delete (int id) throws DAOException, RMSDBException;

}

