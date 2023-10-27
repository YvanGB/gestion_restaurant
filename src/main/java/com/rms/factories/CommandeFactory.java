package com.rms.factories;

import com.rms.dao.CommandeDaoImpl;

public class CommandeFactory extends AbstractFactory{
    public CommandeDaoImpl getCommandeDaoImpl(Class<? extends CommandeDaoImpl> typeCommandeDao){
        if ( typeCommandeDao == null ) {
            return null;
        }

        if (typeCommandeDao == CommandeDaoImpl.class) {
            return new CommandeDaoImpl ();
        }

        return null;
    }
}
