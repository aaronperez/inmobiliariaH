/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import hibernate.Fotos;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author AngelakaMogu
 */
public class ModeloFoto {

    public static List<Fotos> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = " from Fotos ";
        Query q = session.createQuery(hql);
        List<Fotos> fotos = q.list();
        session.getTransaction().commit();
        session.close();
        return fotos;
    }

    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Fotos f = (Fotos) session.load(Fotos.class,Integer.parseInt(id));
        session.delete(f);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Fotos f){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(f);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void update(Fotos f){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(f);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static Fotos get(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Fotos f = (Fotos) session.get(Fotos.class, Integer.parseInt(id));
        
        session.getTransaction().commit();
        session.close();
        
        return f;
    }
    
    public static List <Fotos> getFotos(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM FOTOS F WHERE F.idInmueble = "+id;
        Query q = session.createQuery(hql);
        List<Fotos> fotos = q.list();
        session.getTransaction().commit();
        session.close();
        return fotos;
    }
}
