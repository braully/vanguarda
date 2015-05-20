package br.edu.ifgoiano.vanguarda.ejb

import br.edu.ifgoiano.vanguarda.jpa.Entidade
import java.util.logging.Logger
import java.util.logging.Level
import javax.ejb.Stateless
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query

@Stateless
public class GenericoEjb  {
    
    static final Logger logger = Logger.getLogger("AplicaoVanguardaEjb")
    
    @PersistenceContext(unitName = "defaultPU")
    EntityManager em


    void salvarEntidade(Entidade entidade) {
        if(entidade != null){
            if(entidade.persistido){
                em.merge(entidade)
            } else {
                em.persist(entidade)
            }
            em.flush()
            //Simular demora no servidor
            try {
                Thread.sleep(4000)
            } catch(Exception e) {}
        }        
    }
    
    void removerEntidade(Class classe, Object id) {
        def entidade = em.find(classe, id)
        em.remove(entidade)
    }
    
    public <T> List listarEntidade(Class<T> classe, Object...args) {
        def List<T> lista = null
        if (classe != null) {
            StringBuilder hql = new StringBuilder()
            try {
                hql.append("SELECT DISTINCT e FROM ").append(classe.getSimpleName()).append(" e ")
                Query q = null
                if (args != null && args.length > 0 && (args.length % 2) == 0) {
                    hql.append(" WHERE e.").append(args[0]).append(" = ?")
                    for (int i = 2; i < args.length; i = i + 2) {
                        hql.append(" AND e.").append(args[i]).append(" = ?")
                    }
                }
                q = em.createQuery(hql.toString())
                int j = 1
                if (args != null && args.length > 0 && (args.length % 2) == 0){
                    for (int i = 1; i < args.length; i = i + 2) {
                        hql.append(" AND e.").append(args[i]).append(" = ?")
                        q.setParameter(j++, args[i])
                    }                
                }
                lista = (List<T>) q.getResultList()                
            } catch (Exception e) {
                logger.log(Level.WARNING, "Erro", e)
            }
        }
        return lista
    }
}
