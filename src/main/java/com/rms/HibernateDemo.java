package com.rms;

import com.rms.db.HibernateConnection;
import org.hibernate.Session;


public class HibernateDemo {
    public static void main(String[] args){
            HibernateConnection hibernateConnection = HibernateConnection.getInstance();
            Session session = hibernateConnection.getSession();

            if(session.isOpen()){
                System.out.println("Hibernate a demarré avec succes");
                session.close();
        }else {
            System.out.println("Demarrage echoué");
        }
    }
}
