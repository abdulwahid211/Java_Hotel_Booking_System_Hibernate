import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class DataTransaction {

    // Hold a reusable reference to a SessionFactory
    private static SessionFactory sessionFactory = null;


    public DataTransaction(Class annotatedClass) {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(annotatedClass).buildSessionFactory();
    }

    // delete a object in the db
    public static void delete(Object object) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Use the session to update the object
        session.delete(object);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    // update a object in the db
    public void update(Object object) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.update(object);

        session.getTransaction().commit();

        session.close();

    }

    public static void add(Object c) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Use the session to update the contact
        session.save(c);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @SuppressWarnings("unchecked")
    public static List<Object> fetchAllObject() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Create CriteriaQuery
        CriteriaQuery<Object> criteria = session.getCriteriaBuilder().createQuery(Object.class);
        criteria.from(Object.class);

        List<Object> objects = session.createQuery(criteria).getResultList();

        session.close();

        return objects;
    }

}