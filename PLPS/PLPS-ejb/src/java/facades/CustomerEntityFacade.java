/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.CustomerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class CustomerEntityFacade implements CustomerEntityFacadeLocal, CustomerEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CustomerEntity customerEntity) {
        em.persist(customerEntity);
    }

    public void edit(CustomerEntity customerEntity) {
        em.merge(customerEntity);
    }

    public void remove(CustomerEntity customerEntity) {
        em.remove(em.merge(customerEntity));
    }

    public CustomerEntity find(Object id) {
        return em.find(CustomerEntity.class, id);
    }

    public List<CustomerEntity> findAll() {
        return em.createQuery("select object(o) from Customer as o").getResultList();
    }

}
