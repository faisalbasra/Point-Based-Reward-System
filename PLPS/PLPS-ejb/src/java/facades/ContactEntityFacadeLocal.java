/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ContactEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zen9
 */
@Local
public interface ContactEntityFacadeLocal {

    void create(ContactEntity contactEntity);

    void edit(ContactEntity contactEntity);

    void remove(ContactEntity contactEntity);

    ContactEntity find(Object id);

    List<ContactEntity> findAll();

}
