/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import exceptions.ExistException;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface RedemptionManagerRemote {

    public RedemptionEntity createRedemption(String customerId, Date dateofRedemption);
    public RedemptionEntity addRedemptionLine(Long redemptionId, Long lineId);
    RedemptionLineEntity createRedemptionLine(int quantity, Long rewardId);
    public void deleteRedemption(Long id) throws ExistException;
    
}
