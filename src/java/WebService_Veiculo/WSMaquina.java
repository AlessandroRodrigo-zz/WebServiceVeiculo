/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService_Veiculo;

import com.dao.MaquinaDAO;
import com.dao.VeiculoDAO;
import com.google.gson.Gson;
import com.model.Maquina;
import com.model.Veiculo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ALESSANDRORODRIGOFRE
 */
@Path("wsmaquina")
public class WSMaquina {

    @Context
    private UriInfo context;

    public WSMaquina() {
    }

    Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrarMaquina/novamaquina")
    public String cadastrarMaquinas(String novaMaquina) throws Exception {
        Maquina oMaquina = (Maquina) gson.fromJson(novaMaquina, Maquina.class);
        MaquinaDAO dao = new MaquinaDAO();
        return gson.toJson(dao.Cadastrar(oMaquina));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarMaquinas")
    public String listarMaquinas() throws Exception {
        Maquina oMaquina = new Maquina();
        MaquinaDAO dao = new MaquinaDAO();
        return gson.toJson(dao.Listar());
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removerMaquina/{idMaquina}")
    public String excluirMaquina(@PathParam("idMaquina") int idMaquina) throws Exception {
        Maquina oMaquina = new Maquina();
        oMaquina.setIdMaquina(idMaquina);
        MaquinaDAO dao = new MaquinaDAO();
        return gson.toJson(dao.Excluir(oMaquina));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/carregarMaquina/{idMaquina}")
    public String carregarMaquina(@PathParam("idMaquina") int idMaquina) throws Exception {
        MaquinaDAO dao = new MaquinaDAO();
        return gson.toJson(dao.Carregar(idMaquina));
    }
}
