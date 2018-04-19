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
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import entidade.Aquario;
import rn.AquarioRN;

/**
 * REST Web Service
 *
 * @author leole
 */
@Path("aquario")
public class AquarioWS {

    AquarioRN aquarioRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MotorWS
     */
    public AquarioWS() {
        aquarioRN = new AquarioRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aquario> getAquario() {
        return (aquarioRN.listar());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aquario adicionar(Aquario aquario,
            @Context HttpServletResponse response) {

        aquarioRN.inserir(aquario);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return aquario;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aquario getAquarioPorId(@PathParam("id") Long id) {
        return aquarioRN.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aquario atualiza(@PathParam("id") Long id,
            Aquario aquario){
        aquario.setId(id);
        Aquario aquarioAtualizado = aquarioRN.atualizar(aquario);
        return aquarioAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aquario deletar(@PathParam("id") Long id){
        Aquario aquarioDeletado = aquarioRN.deletar(id);
        return aquarioDeletado;
    }

}
