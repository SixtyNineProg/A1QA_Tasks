package by.a1qa.klimov.dao.utils;

import by.a1qa.klimov.dao.entity.*;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Test.class);
            configuration.addAnnotatedClass(Project.class);
            configuration.addAnnotatedClass(Session.class);
            configuration.addAnnotatedClass(Status.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
