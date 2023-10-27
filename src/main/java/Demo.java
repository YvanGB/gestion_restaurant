import com.rms.db.HibernateConnection;
import com.rms.model.Commande;
import com.rms.model.Paiement;
import com.rms.model.Produit;
import com.rms.model.Recette;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo {
    private static Testeur testeur = new Testeur();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HibernateConnection hibernateConnection = HibernateConnection.getInstance();
        Session session = hibernateConnection.getSession();
        if(session.isOpen()) {
            System.out.println("Hibernate started successfully !");

            testeur.creerAdministrator();
            testeur.creerChef();
            testeur.creerRestaurateur();

            Produit produit = new Produit("Pringles",  "Chips salées et pimentée",2900, "", 2, new Date());
            Produit produit2 = new Produit("Jus OR", "Jus d'orange", 1500,"", 5,new Date());

            List<Produit> products1 = new ArrayList<>();
            products1.add(produit);
            products1.add(produit2);

            Produit produit3 = new Produit("Binto", "Biscuits au chocolat", 500,"",10,new Date());
            Produit produit4 = new Produit("Biscreme", "Biscuit chocolaté",  125,"", 13,new Date());

            List<Produit> products2 = new ArrayList<>();
            products1.add(produit3);
            products1.add(produit4);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateString = sdf.format(date);


            Commande commande = new Commande(products1, date, 4400);
            commande.ajouterProduit(produit);
            commande.ajouterProduit(produit2);

            Commande commande2 = new Commande(products2, date, 2300);
            commande2.ajouterProduit(produit3);
            commande2.ajouterProduit(produit4);

            List<Commande> orders = new ArrayList<>();
            orders.add(commande);
            orders.add(commande2);

            Paiement pay = new Paiement(10555, 2500, orders, date, "");
            pay.ajouterCommande(commande);
            pay.ajouterCommande(commande);

            List<Paiement> paiements = new ArrayList<>();
            paiements.add(pay);

            Recette recette = new Recette(paiements);
            recette.ajouterPaiement(pay);

            // testeur.creerRecipe(payments);
            testeur.creerRecipe(recette);

            session.close();
        } else
            System.out.println("Echec !");
    }
}