package ws;

import entidade.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.ClienteRN;

/**
 * REST Web Service
 *
 * @author leole
 */
@Path("cliente")
public class ClienteWS {

    ClienteRN clienteRN;



  
    public ClienteWS() {
        clienteRN = new ClienteRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getCliente() {
        return (clienteRN.listar());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente adicionar(Cliente cliente,
            @Context HttpServletResponse response) {

        clienteRN.inserir(cliente);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return cliente;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getAquarioPorId(@PathParam("id") Long id) {
        return clienteRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente atualiza(@PathParam("id") Long id,
            Cliente cliente){
        cliente.setId(id);
        Cliente clienteAtualizado = clienteRN.atualizar(cliente);
        return clienteAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente deletar(@PathParam("id") Long id){
        Cliente clienteDeletado = clienteRN.deletar(id);
        return clienteDeletado;
    }

}
