/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import exceptions.DeleteException;
import exceptions.ExistException;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface MerchantManagerRemote {

    public void createMerchant(String id, String name, String address) throws ExistException;
    public void deleteMerchant(String id) throws DeleteException;
}
