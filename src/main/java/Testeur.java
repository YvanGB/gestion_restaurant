
import com.rms.dao.*;
import com.rms.db.RMSDBException;
import com.rms.factories.*;
import com.rms.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Testeur {

    public Testeur() {}

    public User creerUser() {
        UserDaoImpl userDao = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        User user = null;

        try {
            user = new User("UserLN", "UserFN", "userLogin", "userPassword", "Restaurateur");
            userDao.create(user);
            System.out.println("Une nouvelle admin est ajouté !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return user;
    }
    public Administrateur creerAdministrator() {
        AdministrateurDaoImpl adminDao = ConcreteFactory.getFactory(AdministrateurFactory.class)
                .getAdministrateurDaoImpl(AdministrateurDaoImpl.class);
        Administrateur admin = null;

        try {
            admin = new Administrateur("AdminLN", "AdminFN", "adminLogin", "adminPassword", "Administrateur");
            adminDao.create(admin);
            System.out.println("Une nouvelle admin est ajouté !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return admin;
    }

    public ChefCuisinier creerChef() {
        ChefCuisinierDaoImpl chefDao = ConcreteFactory.getFactory(ChefCuisinierFactory.class)
                .getChefCuisinierDaoImpl(ChefCuisinierDaoImpl.class);
        ChefCuisinier chef = null;

        try {
            chef = new ChefCuisinier("ChefLN", "ChefFN", "chefLogin", "chefPassword", "ChefCuisinier");
            chefDao.create(chef);
            System.out.println("Un nouveau chef est ajouté !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return chef;
    }

    public Restaurateur creerRestaurateur() {
        RestaurateurDaoImpl restaurateurDao = ConcreteFactory.getFactory(RestaurateurFactory.class)
                .getRestaurateurDaoImpl(RestaurateurDaoImpl.class);
        Restaurateur restaurateur = null;

        try {
            restaurateur = new Restaurateur("RestaurateurLN", "RestaurateurFN", "restaurateurLogin", "restaurateurPassword", "Restaurateur");
            restaurateurDao.create(restaurateur);
            System.out.println("Un nouveau restaurateur est ajouté !");
        } catch (DAOException | RMSDBException e) {
            System.out.println(e);
        }

        return restaurateur;
    }

    public Produit creerProduit(String intitule, String description, double prix, String categorie, String image, int qte, Date date) {
        ProduitDaoImpl productDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        Produit produit = null;

        try {
            produit = new Produit(intitule, description, prix ,categorie, image, qte, date);
            productDao.create(produit);
            System.out.println("Un nouveau produit est ajouté !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return produit;
    }

    public Commande creerCommande(List<Produit> produits) {
        CommandeDaoImpl orderDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        Commande commande = null;

        double totalPrice = 0;
        for(Produit produit : produits) {
            totalPrice += produit.getPrix();
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(date);

        try {
            commande = new Commande(produits, date, totalPrice);
            orderDao.create(commande);
            System.out.println("Une nouvelle commande est ajoutée !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return commande;
    }

    public Paiement creerPaiement(double payrollAmount, double remainder, String paiementDate, List<Commande> commandes) {
        PaiementDaoImpl paymentDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        Paiement paiement = null;

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(date);

        try {
            paiement = new Paiement(payrollAmount, remainder, commandes, date, "");
            paymentDao.create(paiement);
            System.out.println("Une nouvelle commande est ajoutée !");
        } catch (DAOException | RMSDBException e) {
            System.out.println(e);
        }

        return paiement;
    }


    public Recette creerRecipe(List<Paiement> paiements) {
        RecetteDaoImpl recipeDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        Recette recette = null;

        try {
            recette = new Recette(paiements);

            for(Paiement pay : paiements)
                recette.ajouterPaiement(pay);

            recipeDao.create(recette);
            System.out.println("Une nouvelle recette est ajoutée !");
        } catch (DAOException e) {
            System.out.println(e);
        } catch (RMSDBException e) {
            System.out.println(e);
        }

        return recette;
    }

    public Recette creerRecipe(Recette recette) {
        RecetteDaoImpl recipeDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            recipeDao.create(recette);
            System.out.println("Une nouvelle recette est ajoutée !");
        } catch (DAOException | RMSDBException e) {
            System.out.println(e);
        }

        return recette;
    }
}