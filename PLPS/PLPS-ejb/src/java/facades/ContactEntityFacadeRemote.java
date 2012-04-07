/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ContactEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface ContactEntityFacadeRemote {

    void create(ContactEntity contactEntity);

    void edit(ContactEntity contactEntity);

    void remove(ContactEntity contactEntity);

    ContactEntity find(Object id);

    List<ContactEntity> findAll();

}
