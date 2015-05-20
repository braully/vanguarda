package br.edu.ifgoiano.vanguarda.ejb


import br.edu.ifgoiano.vanguarda.jpa.Pessoa
import java.util.logging.Logger
import java.util.logging.Level
import javax.annotation.PostConstruct
import javax.ejb.*
import javax.ejb.TransactionAttribute
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query

@Startup
@Singleton
class AplicaoVanguardaEjb {
    
    static final Logger logger = Logger.getLogger("AplicaoVanguardaEjb")
    
    @PersistenceContext(unitName = "defaultPU")
    EntityManager em
    
    @PostConstruct
    @TransactionAttribute
    public void ini(){
        def count = 0
        try {
            count = em.createQuery("SELECT COUNT(id) FROM Pessoa").getSingleResult()
        } catch(Exception e){
            logger.log(Level.WARNING, "Falha na consulta de pessoas", e);
        }
        if(count == 0){
            em.persist(new Pessoa(nome: 'Fulano de Tal', email: 'fulano@tal.com.br'))
            em.persist(new Pessoa(nome: 'Cicalno de Tal', email: 'ciclano@tal.com.br'))
            em.persist(new Pessoa(nome: 'Beltrano de Tal', email: 'beltrano@tal.com.br'))
        }
    }	
}

