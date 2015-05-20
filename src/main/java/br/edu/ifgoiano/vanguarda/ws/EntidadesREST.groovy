package br.edu.ifgoiano.vanguarda.ws

import br.edu.ifgoiano.vanguarda.jpa.*
import br.edu.ifgoiano.vanguarda.ejb.*
import java.util.List
import javax.ejb.Stateless
import javax.ws.rs.*
import javax.ws.rs.PUT
import javax.ws.rs.core.*
import javax.ejb.*

/**
 *
 * @author strike
 */
@Stateless
@ApplicationPath("/entidade")
@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EntidadesREST extends Application  {

    @EJB
    GenericoEjb genericoEJB

    @POST
    @PUT
    //    @Consumes(["application/xml", "application/json"])
    @Consumes(MediaType.APPLICATION_JSON)
    public Pessoa salvarPessoa(Pessoa entidade) {
        genericoEJB.salvarEntidade(entidade)
        return entidade
    }

    @DELETE
    @Path("{id}")
    public void removerPessoa(@PathParam("id") Long id) {
        genericoEJB.removerEntidade(Pessoa.class, id)
    }

    @GET
    @Path("{id}")
    //    @Produces(["application/xml", "application/json"])
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa buscarPessoa(@PathParam("id") Long id) {
        return genericoEJB.listarEntidade(Pessoa.class, id).get(0)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listPessoa() {
        genericoEJB.listarEntidade(Pessoa.class)
    }
}
