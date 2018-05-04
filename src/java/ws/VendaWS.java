package ws;

import entidade.Aquario;
import entidade.Peixe;
import entidade.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rn.VendaRN;

/**
 * REST Web Service
 *
 * @author leole
 */
@Path("venda")
public class VendaWS {
    private VendaRN vendaRN;

    @Context
    private UriInfo context;

  
    public VendaWS() {
        vendaRN=new VendaRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venda> getAquario() {
        return (vendaRN.listar());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Venda gerarVenda(Venda venda, @Context HttpServletResponse response) throws IOException{
        try{
            vendaRN.vender(venda);
        }catch(Exception e){
            try{
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                PrintWriter w = response.getWriter();
                w.write(e.getMessage());
                w.flush();
            }catch(IOException e3){
                
            }
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        } catch (IOException ex){
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return venda;
    }
}
