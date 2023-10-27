package com.rms.factories;

import com.rms.dao.PaiementDaoImpl;

public class PaiementFactory extends AbstractFactory{
    public PaiementDaoImpl getPaiementDaoImpl(Class<? extends PaiementDaoImpl> typePaiementDao){
        if(typePaiementDao == null){
            return null;
        }

        if (typePaiementDao == PaiementDaoImpl.class) {
            return new PaiementDaoImpl ();
        }

        return null;
    }
}
