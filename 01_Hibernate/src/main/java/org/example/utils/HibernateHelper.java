package org.example.utils;

import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// DbContext in C#
public class HibernateHelper {
    private static SessionFactory sessionFactory;

    // Буде викликатись автоматикчно коли буде перший раз виклоистовуватися даний клас
    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(CategoryEntity.class)
                    .addAnnotatedClass(ProductEntity.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Database exception: " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shoutDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
