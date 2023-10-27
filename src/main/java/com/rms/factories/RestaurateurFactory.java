package com.rms.factories;

import com.rms.dao.RestaurateurDaoImpl;

public class RestaurateurFactory extends AbstractFactory{
    public RestaurateurDaoImpl getRestaurateurDaoImpl (Class<? extends RestaurateurDaoImpl> typeRestaurateurDao) {
        if ( typeRestaurateurDao == null ) {
            return null;
        }

        if (typeRestaurateurDao == RestaurateurDaoImpl.class) {
            return new RestaurateurDaoImpl ();
        }

        return null;
    }
}
