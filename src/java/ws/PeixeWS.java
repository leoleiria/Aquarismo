package ws;

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
import entidade.Peixe;
import rn.PeixeRN;

/**
 * REST Web Service
 *
 * @author leole
 */
@Path("aquario")
public class PeixeWS {

    PeixeRN peixeRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MotorWS
     */
    public PeixeWS() {
        peixeRN = new PeixeRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Peixe> getPeixe() {
        return (peixeRN.listar());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Peixe adicionar(Peixe peixe,
            @Context HttpServletResponse response) {

        peixeRN.inserir(peixe);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return peixe;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Peixe getPeixePorId(@PathParam("id") Long id) {
        return PeixeRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Peixe atualiza(@PathParam("id") Long id,
            Peixe peixe){
        peixe.setId(id);
        Peixe peixeAtualizado = peixeRN.atualizar(peixe);
        return peixeAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Peixe deletar(@PathParam("id") Long id){
        Peixe peixeDeletado = peixeRN.deletar(id);
        return peixeDeletado;
    }
}