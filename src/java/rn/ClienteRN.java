package rn;

import entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class ClienteRN {

    public ClienteRN() {

    }

    public Cliente inserir(Cliente cliente) {
        
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();

        manager.close();

        return (cliente);

    }

    public Cliente buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Cliente cliente = manager.find(Cliente.class, id);
        //Verificacao de id
        manager.close();
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        cliente = manager.merge(cliente);
        manager.getTransaction().commit();

        manager.close();

        return (cliente);
    }

    public Cliente deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Cliente cliente = manager.find(Cliente.class, id);

        manager.getTransaction().begin();
        manager.remove(cliente);
        manager.getTransaction().commit();

        manager.close();

        return (cliente);

    }

    public List<Cliente> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Cliente m");
        List<Cliente> listaClientes = query.getResultList();
        manager.close();
        return listaClientes;
    }

}
