/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import entities.CustomerEntity;
import exceptions.DeleteException;
import exceptions.ExistException;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface CustomerManagerRemote {
    public void createCustomer(String id, String name, String password, String address) throws ExistException;
    public void deleteCustomer(String id) throws DeleteException;
    public CustomerEntity loginCustomer(String id, String password);
}
