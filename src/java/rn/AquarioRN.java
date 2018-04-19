package rn;

import entidade.Aquario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class AquarioRN {

    public AquarioRN() {

    }

    public Aquario inserir(Aquario aquario) {
        
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(aquario);
        manager.getTransaction().commit();

        manager.close();

        return (aquario);

    }

    public Aquario buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Aquario aquario = manager.find(Aquario.class, id);
        //Verificacao de id
        manager.close();
        return aquario;
    }

    public Aquario atualizar(Aquario aquario) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        aquario = manager.merge(aquario);
        manager.getTransaction().commit();

        manager.close();

        return (aquario);
    }

    public Aquario deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Aquario aquario = manager.find(Aquario.class, id);

        manager.getTransaction().begin();
        manager.remove(aquario);
        manager.getTransaction().commit();

        manager.close();

        return (aquario);

    }

    public List<Aquario> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Aquario m");
        List<Aquario> listaAquarios = query.getResultList();
        manager.close();
        return listaAquarios;
    }

}
