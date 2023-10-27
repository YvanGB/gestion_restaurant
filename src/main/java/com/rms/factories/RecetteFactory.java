package com.rms.factories;

import com.rms.dao.RecetteDaoImpl;

public class RecetteFactory extends AbstractFactory{
    public RecetteDaoImpl getRecetteDaImpl(Class<? extends RecetteDaoImpl> typeRecetteDao){
        if(typeRecetteDao == null){
            return null;
        }

        if(typeRecetteDao == RecetteDaoImpl.class){
            return new RecetteDaoImpl();
        }

        return null;
    }
}
