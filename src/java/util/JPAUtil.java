
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author leole
 */
public class JPAUtil {
    private static final String NOMEPU= "Aquario";
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory(NOMEPU);
    
    public static EntityManager createManager(){
        return factory.createEntityManager();
    }
}

