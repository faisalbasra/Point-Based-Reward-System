/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RedemptionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class RedemptionEntityFacade implements RedemptionEntityFacadeLocal, RedemptionEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(RedemptionEntity redemptionEntity) {
        em.persist(redemptionEntity);
    }

    public void edit(RedemptionEntity redemptionEntity) {
        em.merge(redemptionEntity);
    }

    public void remove(RedemptionEntity redemptionEntity) {
        em.remove(em.merge(redemptionEntity));
    }

    public RedemptionEntity find(Object id) {
        return em.find(RedemptionEntity.class, id);
    }

    public List<RedemptionEntity> findAll() {
        return em.createQuery("select object(o) from Redemption as o").getResultList();
    }

}
