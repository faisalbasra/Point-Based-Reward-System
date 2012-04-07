/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RedemptionLineEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class RedemptionLineEntityFacade implements RedemptionLineEntityFacadeLocal, RedemptionLineEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(RedemptionLineEntity redemptionLineEntity) {
        em.persist(redemptionLineEntity);
    }

    public void edit(RedemptionLineEntity redemptionLineEntity) {
        em.merge(redemptionLineEntity);
    }

    public void remove(RedemptionLineEntity redemptionLineEntity) {
        em.remove(em.merge(redemptionLineEntity));
    }

    public RedemptionLineEntity find(Object id) {
        return em.find(RedemptionLineEntity.class, id);
    }

    public List<RedemptionLineEntity> findAll() {
        return em.createQuery("select object(o) from RedemptionLine as o").getResultList();
    }

}
