/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ContactEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zen9
 */
@Stateless
public class ContactEntityFacade implements ContactEntityFacadeLocal, ContactEntityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ContactEntity contactEntity) {
        em.persist(contactEntity);
    }

    public void edit(ContactEntity contactEntity) {
        em.merge(contactEntity);
    }

    public void remove(ContactEntity contactEntity) {
        em.remove(em.merge(contactEntity));
    }

    public ContactEntity find(Object id) {
        return em.find(ContactEntity.class, id);
    }

    public List<ContactEntity> findAll() {
        return em.createQuery("select object(o) from Contact as o").getResultList();
    }

}
