/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.MerchantEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class MerchantEntityFacade implements MerchantEntityFacadeLocal, MerchantEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(MerchantEntity merchantEntity) {
        em.persist(merchantEntity);
    }

    public void edit(MerchantEntity merchantEntity) {
        em.merge(merchantEntity);
    }

    public void remove(MerchantEntity merchantEntity) {
        em.remove(em.merge(merchantEntity));
    }

    public MerchantEntity find(Object id) {
        return em.find(MerchantEntity.class, id);
    }

    public List<MerchantEntity> findAll() {
        return em.createQuery("select object(o) from Merchant as o").getResultList();
    }

}
