package com.rms.factories;

import com.rms.dao.*;

public class AbstractFactory {
    public RestaurateurDaoImpl getRestaurateurDaoImpl(Class<? extends RestaurateurDaoImpl> typeRestaurateurDao){
        return null;
    }

    public ChefCuisinierDaoImpl getChefCuisinierDaoImpl(Class<? extends ChefCuisinierDaoImpl> typeChefCuisinierDao){
        return null;
    }

    public AdministrateurDaoImpl getAdministrateurDaoImpl(Class<? extends AdministrateurDaoImpl> typeAdminDao){
        return null;
    }
    public UserDaoImpl getUserDaoImpl(Class<? extends  UserDaoImpl> typeUserDao){
        return null;
    }

    public ProduitDaoImpl getProduitDaoImpl(Class<? extends ProduitDaoImpl> typeProduitDao){
        return null;
    }

    public CommandeDaoImpl getCommandeDaoImpl(Class<? extends CommandeDaoImpl> typeCommandeDao){
        return null;
    }

    public PaiementDaoImpl getPaiementDaoImpl(Class<? extends PaiementDaoImpl> typePaiementDao){
        return null;
    }
    public RecetteDaoImpl getRecetteDaImpl(Class<? extends RecetteDaoImpl> typeRecetteDao){
        return null;
    }
}
