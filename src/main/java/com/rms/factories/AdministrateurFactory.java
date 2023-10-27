package com.rms.factories;

import com.rms.dao.AdministrateurDaoImpl;

public class AdministrateurFactory extends AbstractFactory{
    public AdministrateurDaoImpl getAdministrateurDaoImpl (Class<? extends AdministrateurDaoImpl> typeAdminDao) {
        if ( typeAdminDao == null ) {
            return null;
        }

        if (typeAdminDao == AdministrateurDaoImpl.class) {
            return new AdministrateurDaoImpl ();
        }

        return null;
    }
}
