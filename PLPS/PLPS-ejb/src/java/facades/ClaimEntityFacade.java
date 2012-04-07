/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ClaimEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class ClaimEntityFacade implements ClaimEntityFacadeLocal, ClaimEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ClaimEntity claimEntity) {
        em.persist(claimEntity);
    }

    public void edit(ClaimEntity claimEntity) {
        em.merge(claimEntity);
    }

    public void remove(ClaimEntity claimEntity) {
        em.remove(em.merge(claimEntity));
    }

    public ClaimEntity find(Object id) {
        return em.find(ClaimEntity.class, id);
    }

    public List<ClaimEntity> findAll() {
        return em.createQuery("select object(o) from Claim as o").getResultList();
    }

}
