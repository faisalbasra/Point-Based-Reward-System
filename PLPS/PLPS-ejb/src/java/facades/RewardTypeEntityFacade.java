/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RewardTypeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class RewardTypeEntityFacade implements RewardTypeEntityFacadeLocal, RewardTypeEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(RewardTypeEntity rewardTypeEntity) {
        em.persist(rewardTypeEntity);
    }

    public void edit(RewardTypeEntity rewardTypeEntity) {
        em.merge(rewardTypeEntity);
    }

    public void remove(RewardTypeEntity rewardTypeEntity) {
        em.remove(em.merge(rewardTypeEntity));
    }

    public RewardTypeEntity find(Object id) {
        return em.find(RewardTypeEntity.class, id);
    }

    public List<RewardTypeEntity> findAll() {
        return em.createQuery("select object(o) from RewardType as o").getResultList();
    }

}
