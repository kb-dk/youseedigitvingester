package dk.statsbiblioteket.digitv.youseeingester.model.persistence;

import dk.statsbiblioteket.digitaltv.utils.constants.PropertyNames;
import dk.statsbiblioteket.digitv.persistence.HibernateUtilIF;
import dk.statsbiblioteket.digitv.persistence.NotInitialiasedException;
import dk.statsbiblioteket.digitv.persistence.recordedfile.RecordedFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * @author jrg
 */
public class YouseeDigitvIngesterHibernateUtil implements HibernateUtilIF {
    private YouseeDigitvIngesterHibernateUtil() {

    }

    private static SessionFactory sessionFactory;

    public static HibernateUtilIF getInitialisedFactory() throws NotInitialiasedException {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            return new YouseeDigitvIngesterHibernateUtil();
        } else {
            throw new NotInitialiasedException("Attempt to access uninitialised Hibernate utility.");
        }
    }

    /**
     * Initialise hibernate from a configuration file on first call. Subsequent calls will not reinitialise the
     * hibernate connection unless the sessionFactory is first closed.
     * @param cfgFile  The configuration file.
     * @return An instance of this class.
     */
    public static HibernateUtilIF initialiseFactory(File cfgFile) {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                if (sessionFactory == null) {
                    Configuration configure = (new Configuration()).configure(cfgFile);
                    configure.addAnnotatedClass(RecordedFile.class);
                    sessionFactory = configure.buildSessionFactory();
                }
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                ex.printStackTrace();
                throw new ExceptionInInitializerError(ex);
            }
        }
        return new YouseeDigitvIngesterHibernateUtil();
    }

    /**
     * Gets a session factory
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Gets a hibernate session. This class must be initialised before this method is called.
     * @return a Session
     */
    public Session getSession() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            throw new RuntimeException("Attempt to use ChannelArchivingRequesterHibernateUtil before it was initialised or " +
                    "after sessionFactory was closed");
        }
        return sessionFactory.openSession();
    }
}
