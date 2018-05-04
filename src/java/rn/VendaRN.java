package rn;

import entidade.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author leole
 */
public class VendaRN {

    public VendaRN() {

    }

    public Venda vender(Venda venda) throws Exception{
        EntityManager manager = JPAUtil.createManager();
        /*
        Query query = manager.createQuery("Select COUNT(v.id) from Venda v WHERE v.listPeixe.estoque");
        Object resultCount = query.getSingleResult();
        if((Long) resultCount > 0){
            throw new Exception("Não há estoque deste Peixe!");
        }*/
        
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();

        manager.close();

        return (venda);

    }

    public static Venda buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Venda aquario = manager.find(Venda.class, id);
        //Verificacao de id
        manager.close();
        return aquario;
    }
    
    public Venda deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Venda venda = manager.find(Venda.class, id);

        manager.getTransaction().begin();
        manager.remove(venda);
        manager.getTransaction().commit();

        manager.close();

        return (venda);

    }

    public List<Venda> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Venda m");
        List<Venda> listaVenda = query.getResultList();
        manager.close();
        return listaVenda;
    }
    
    

}


