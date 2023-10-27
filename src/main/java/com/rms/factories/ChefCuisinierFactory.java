package com.rms.factories;

import com.rms.dao.ChefCuisinierDaoImpl;

public class ChefCuisinierFactory extends AbstractFactory {
    public ChefCuisinierDaoImpl getChefCuisinierDaoImpl (Class<? extends ChefCuisinierDaoImpl> typeChefCuisinierDao) {
        if ( typeChefCuisinierDao == null ) {
            return null;
        }

        if (typeChefCuisinierDao == ChefCuisinierDaoImpl.class) {
            return new ChefCuisinierDaoImpl ();
        }

        return null;
    }
}
