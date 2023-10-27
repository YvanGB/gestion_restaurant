package com.rms.factories;

import com.rms.dao.ProduitDaoImpl;

public class ProduitFactory extends AbstractFactory{
    public ProduitDaoImpl getProduitDaoImpl(Class<? extends ProduitDaoImpl> typeProduitDao) {
        if ( typeProduitDao == null ) {
            return null;
        }

        if (typeProduitDao == ProduitDaoImpl.class) {
            return new ProduitDaoImpl ();
        }

        return null;
    }
}
