package com.rms.db;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rms.dao.*;
import com.rms.factories.*;
import com.rms.model.*;
import org.hibernate.Session;

public class AccessDB {
    private static AccessDB instance = null;

    public static synchronized AccessDB getInstance() {
        if (instance == null) {
            instance = new AccessDB();
        }
        return instance;
    }

    private AccessDB() {
        HibernateConnection hibernateConnection = HibernateConnection.getInstance();
        Session session = hibernateConnection.getSession();
        if(session.isOpen()) {
            System.out.println("Hibernate a bien démarré !");
        } else {
            System.out.println("Echec de demarrage hibernate !");
        }
    }


    // Les appels Dao

    public User readUser(String login, String password) {
        UserDaoImpl userDao  = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        User user = null;
        try {
            List<User> users = userDao.list();
            for(User u : users) {
                if(u.getLogin().equalsIgnoreCase(login) && u.getPassword().equalsIgnoreCase(password)) {
                    user = u;
                }
            }
        } catch (DAOException | RMSDBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUsers() {
        UserDaoImpl userDao  = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        List<User> users = new ArrayList<>();
        try {
            users = userDao.list();
        } catch (DAOException | RMSDBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }

    public User addUser(User user) {
        UserDaoImpl userDao = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        try {
            userDao.create(user);
            System.out.println("Une nouvel utilisateur est ajouté !");
        } catch (DAOException | RMSDBException e) {
            System.out.println("**err**: "+e);
        }
        return user;
    }

    public User updateUser(User user) {
        UserDaoImpl userDao = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        try {
            userDao.update(user);
            System.out.println("Utilisateur est modifié !");
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User deleteUser(User user) {
        UserDaoImpl userDao = ConcreteFactory.getFactory(UserFactory.class)
                .getUserDaoImpl(UserDaoImpl.class);
        try {
            userDao.delete(user.getId());
            System.out.println("Utilisateur est supprimé !");
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return user;
    }


//Appel dao pour Produit

    public Produit readProduit(int id) {
        ProduitDaoImpl produitDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        try {
            return produitDao.read(id);
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupération de la liste des produits
    public List<Produit> getProduits() {
        ProduitDaoImpl produitDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        try {
            return produitDao.list();
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();

        }
        return new ArrayList<>();
    }

    // Ajout d'un nouveau produit
    public Produit addProduit(Produit produit) {
        ProduitDaoImpl produitDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        try {
            produitDao.create(produit);
            System.out.println("Un nouveau produit a été ajouté !");
            return produit;
        } catch (DAOException | RMSDBException e) {

        }
        return null;
    }

    // Mise à jour d'un produit existant
    public Produit updateProduit(Produit produit) {
        ProduitDaoImpl produitDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        try {
            produitDao.update(produit);
            System.out.println("Le produit a été mis à jour !");
            return produit;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Suppression d'un produit
    public Produit deleteProduit(Produit produit) {
        ProduitDaoImpl produitDao = ConcreteFactory.getFactory(ProduitFactory.class)
                .getProduitDaoImpl(ProduitDaoImpl.class);
        try {
            produitDao.delete(produit.getId());
            System.out.println("Le produit a été supprimé !");
            return produit;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Les dao pour commandes

    // Lecture d'une commande par son identifiant
    public Commande readCommande(int id) {
        CommandeDaoImpl commandeDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        try {
            return commandeDao.read(id);
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
            return null;
    }

    // Récupération de la liste des commandes
    public List<Commande> getCommandes() {
        CommandeDaoImpl commandeDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        try {
            return commandeDao.list();
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Ajout d'une nouvelle commande
    public Commande addCommande(Commande commande) {
        CommandeDaoImpl commandeDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        try {
            commandeDao.create(commande);
            System.out.println("Une nouvelle commande a été ajoutée !");
            return commande;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mise à jour d'une commande existante
    public Commande updateCommande(Commande commande) {
        CommandeDaoImpl commandeDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        try {
            commandeDao.update(commande);
            System.out.println("La commande a été mise à jour !");
            return commande;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Suppression d'une commande
    public Commande deleteCommande(Commande commande) {
        CommandeDaoImpl commandeDao = ConcreteFactory.getFactory(CommandeFactory.class)
                .getCommandeDaoImpl(CommandeDaoImpl.class);
        try {
            commandeDao.delete(commande.getId());
            System.out.println("La commande a été supprimée !");
            return commande;
        } catch (DAOException | RMSDBException e) {
           e.printStackTrace();
        }
        return null;
    }


    //Les dao pour le paiement
    // Lecture d'un paiement par son identifiant
    public Paiement readPaiement(int id) {
        PaiementDaoImpl paiementDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        try {
            return paiementDao.read(id);
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la liste des paiements
    public List<Paiement> getPaiements() {
        PaiementDaoImpl paiementDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        try {
            return paiementDao.list();
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Ajout d'un nouveau paiement
    public Paiement addPaiement(Paiement paiement) {
        PaiementDaoImpl paiementDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        try {
            paiementDao.create(paiement);
            System.out.println("Un nouveau paiement a été ajouté !");
            return paiement;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mise à jour d'un paiement existant
    public Paiement updatePaiement(Paiement paiement) {
        PaiementDaoImpl paiementDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        try {
            paiementDao.update(paiement);
            System.out.println("Le paiement a été mis à jour !");
            return paiement;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Suppression d'un paiement
    public Paiement deletePaiement(Paiement paiement) {
        PaiementDaoImpl paiementDao = ConcreteFactory.getFactory(PaiementFactory.class)
                .getPaiementDaoImpl(PaiementDaoImpl.class);
        try {
            paiementDao.delete(paiement.getId());
            System.out.println("Le paiement a été supprimé !");
            return paiement;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la liste des recettes
    public List<Recette> getRecette() {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            return recetteDao.list();
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    public List<Recette> getRecettesForPeriod(Date date) {
//        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
//                .getRecetteDaImpl(RecetteDaoImpl.class);
//        try {
//            return recetteDao.listByPeriod(date);
//        } catch (DAOException | RMSDBException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
/*******************/
//    public List<Recette> getRecettesForDailyPeriod(Date date) {
//        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
//                .getRecetteDaImpl(RecetteDaoImpl.class);
//        try {
//            Calendar calendar = Calendar.getInstance();
////            Date ldate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            calendar.setTime(date);
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            Date startDate = calendar.getTime();
//            LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//
//            // Obtenez la date de fin de la journée en cours
//            calendar.set(Calendar.HOUR_OF_DAY, 23);
//            calendar.set(Calendar.MINUTE, 59);
//            calendar.set(Calendar.SECOND, 59);
//            calendar.set(Calendar.MILLISECOND, 999);
//            Date endDate = calendar.getTime();
//            LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//
//            // Appelez la méthode listByPeriod avec les dates de début et de fin calculées
//            return recetteDao.listByPeriod(localStartDate, localEndDate);
//        } catch (DAOException | RMSDBException e) {
//            e.printStackTrace();
//        }
//        // Obtenez la date de début de la journée en cours
//        return new ArrayList<>();
//    }

// Lecture d'une recette par son identifiant
public Recette readRecette(int id) {
    RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
            .getRecetteDaImpl(RecetteDaoImpl.class);
    try {
        return recetteDao.read(id);
    } catch (DAOException | RMSDBException e) {
        e.printStackTrace();
    }
    return null;
}

    // Récupération de la liste des recettes
    public List<Recette> getRecettes() {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            return recetteDao.list();
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Ajout d'une nouvelle recette
    public Recette addRecette(Recette recette) {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            recetteDao.create(recette);
            System.out.println("Une nouvelle recette a été ajoutée !");
            return recette;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mise à jour d'une recette existante
    public Recette updateRecette(Recette recette) {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            recetteDao.update(recette);
            System.out.println("La recette a été mise à jour !");
            return recette;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Suppression d'une recette
    public Recette deleteRecette(Recette recette) {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            recetteDao.delete(recette.getId());
            System.out.println("La recette a été supprimée !");
            return recette;
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return null;
    }


public List<Recette> getRecettesForDailyPeriod(Date date) {
    RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
            .getRecetteDaImpl(RecetteDaoImpl.class);
    try {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Obtenez la date de fin de la journée en cours
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endDate = calendar.getTime();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Appelez la méthode listByPeriod avec les dates de début et de fin calculées
        return recetteDao.listByPeriod(localStartDate, localEndDate);
    } catch (DAOException | RMSDBException e) {
        e.printStackTrace();
    }
    // Obtenez la date de début de la journée en cours
    return new ArrayList<>();
}

//    public List<Recette> getRecettesForWeeklyPeriod(Date date) {
//        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
//                .getRecetteDaImpl(RecetteDaoImpl.class);
//        try {
//        Calendar calendar = Calendar.getInstance();
////        Date ldate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date startDate = calendar.getTime();
//        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            calendar.add(Calendar.WEEK_OF_YEAR, 1);
//        calendar.add(Calendar.MILLISECOND, -1);
//        Date endDate = calendar.getTime();
//        LocalDate localEndDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//
//            return recetteDao.listByPeriod(localStartDate, localEndDate);
//        } catch (DAOException | RMSDBException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }

    public List<Recette> getRecettesForWeeklyPeriod(Date date) {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startDate = calendar.getTime();
            LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            calendar.add(Calendar.MILLISECOND, -1);
            Date endDate = calendar.getTime();
            LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return recetteDao.listByPeriod(localStartDate, localEndDate);
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    public List<Recette> getRecettesForMonthlyPeriod(Date date) {
//        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
//                .getRecetteDaImpl(RecetteDaoImpl.class);
//        try {
//        Calendar calendar = Calendar.getInstance();
////        Date ldate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date startDate = calendar.getTime();
//        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//
//            // Obtenez la date de fin du mois en cours
//        calendar.add(Calendar.MONTH, 1);
//        calendar.add(Calendar.MILLISECOND, -1);
//        Date endDate = calendar.getTime();
//        LocalDate localEndDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        // Appelez la méthode listByPeriod avec les dates de début et de fin calculées
//        return recetteDao.listByPeriod(localStartDate, localEndDate);
//        } catch (DAOException | RMSDBException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }

    public List<Recette> getRecettesForMonthlyPeriod(Date date) {
        RecetteDaoImpl recetteDao = ConcreteFactory.getFactory(RecetteFactory.class)
                .getRecetteDaImpl(RecetteDaoImpl.class);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startDate = calendar.getTime();
            LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Obtenez la date de fin du mois en cours
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.MILLISECOND, -1);
            Date endDate = calendar.getTime();
            LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Appelez la méthode listByPeriod avec les dates de début et de fin calculées
            return recetteDao.listByPeriod(localStartDate, localEndDate);
        } catch (DAOException | RMSDBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();


    }
}