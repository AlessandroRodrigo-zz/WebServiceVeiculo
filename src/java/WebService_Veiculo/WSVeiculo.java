/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService_Veiculo;

import com.dao.VeiculoDAO;
import com.google.gson.Gson;
import com.model.Veiculo;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ALESSANDRORODRIGOFRE
 */
@Path("wsveiculo")
public class WSVeiculo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSVeiculo
     */
    public WSVeiculo() {
    }

    Gson gson = new Gson();

    /**
     * Retrieves representation of an instance of WebService_Veiculo.WSVeiculo
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrarVeiculo/novoveiculo")
    public String cadastrarVeiculos(String novoVeiculo) throws Exception {

        Veiculo oVeiculo = (Veiculo) gson.fromJson(novoVeiculo, Veiculo.class);
        VeiculoDAO dao = new VeiculoDAO();
        String resposta = gson.toJson(dao.Cadastrar(oVeiculo));
        return resposta;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarVeiculos")
    public String listarVeiculos() throws Exception {
        Veiculo oVeiculo = new Veiculo();
        VeiculoDAO dao = new VeiculoDAO();
        String resposta = gson.toJson(dao.Listar());
        return resposta;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removerVeiculo/{idVeiculo}")
    public String excluirVeiculo(@PathParam("idVeiculo") int idVeiculo) throws Exception {
        Veiculo oVeiculo = new Veiculo();
        oVeiculo.setIdVeiculo(idVeiculo);
        VeiculoDAO dao = new VeiculoDAO();
        String resposta = dao.Excluir(oVeiculo);
        return gson.toJson(resposta);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/carregarVeiculo/{idVeiculo}")
    public String carregarVeiculo(@PathParam("idVeiculo") int idVeiculo) throws Exception {
        Veiculo oVeiculo = new Veiculo();
        oVeiculo.setIdVeiculo(idVeiculo);
        VeiculoDAO dao = new VeiculoDAO();
        String resposta = gson.toJson(dao.Carregar(idVeiculo));
        return resposta;
    }
}
