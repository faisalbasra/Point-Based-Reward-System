/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.CustomerEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zen9
 */
@Local
public interface CustomerEntityFacadeLocal {

    void create(CustomerEntity customerEntity);

    void edit(CustomerEntity customerEntity);

    void remove(CustomerEntity customerEntity);

    CustomerEntity find(Object id);

    List<CustomerEntity> findAll();

}
