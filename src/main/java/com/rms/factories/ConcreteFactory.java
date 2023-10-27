package com.rms.factories;

public class ConcreteFactory {
    public ConcreteFactory() { }

    public static AbstractFactory getFactory (Class<? extends AbstractFactory> factory) {
        if (factory == null) {
            return null;
        }

        if (factory == ChefCuisinierFactory.class) {
            return new ChefCuisinierFactory ();
        } else if (factory == RestaurateurFactory.class) {
            return new RestaurateurFactory ();
        } else if (factory == AdministrateurFactory.class) {
            return new AdministrateurFactory ();
        } else if (factory == UserFactory.class) {
            return new UserFactory();
        } else if(factory == ProduitFactory.class){
            return new ProduitFactory();
        }else if(factory == CommandeFactory.class){
            return new CommandeFactory();
        }else if(factory == PaiementFactory.class){
            return new PaiementFactory();
        }else if(factory == RecetteFactory.class){
            return new RecetteFactory();
        }

        return null;
    }
}
