package rn;

import entidade.Peixe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author leole
 */
public class PeixeRN {

    public PeixeRN() {

    }

    public Peixe inserir(Peixe peixe) {
        
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(peixe);
        manager.getTransaction().commit();

        manager.close();

        return (peixe);

    }

    public static Peixe buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Peixe aquario = manager.find(Peixe.class, id);
        //Verificacao de id
        manager.close();
        return aquario;
    }

    public Peixe atualizar(Peixe peixe) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        peixe = manager.merge(peixe);
        manager.getTransaction().commit();

        manager.close();

        return (peixe);
    }

    public Peixe deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Peixe peixe = manager.find(Peixe.class, id);

        manager.getTransaction().begin();
        manager.remove(peixe);
        manager.getTransaction().commit();

        manager.close();

        return (peixe);

    }

    public List<Peixe> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Peixe m");
        List<Peixe> listaPeixe = query.getResultList();
        manager.close();
        return listaPeixe;
    }

}


