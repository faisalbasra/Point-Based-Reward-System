/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import entities.RewardTypeEntity;
import exceptions.ExistException;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface RewardManagerRemote {

    public RewardTypeEntity addRewardType(String name, int points);
    public void deleteRewardType(Long id) throws ExistException;
    public void associateMerchant(Long id, String merchantId) throws ExistException;
    
}
