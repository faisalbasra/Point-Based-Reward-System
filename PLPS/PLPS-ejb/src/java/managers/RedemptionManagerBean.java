/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import entities.CustomerEntity;
import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import entities.RewardTypeEntity;
import exceptions.ExistException;
import facades.CustomerEntityFacadeLocal;
import facades.RedemptionEntityFacadeLocal;
import facades.RedemptionLineEntityFacadeLocal;
import facades.RewardTypeEntityFacadeLocal;
import java.util.Date;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author zen9
 */
@Stateful
public class RedemptionManagerBean implements RedemptionManagerRemote {
    @EJB
    private RewardTypeEntityFacadeLocal rewardTypeEntityFacade;
    @EJB
    private CustomerEntityFacadeLocal customerEntityFacade;
    @EJB
    private RedemptionLineEntityFacadeLocal redemptionLineEntityFacade;
    @EJB
    private RedemptionEntityFacadeLocal redemptionEntityFacade;

    public RedemptionEntity createRedemption(String customerId, Date dateofRedemption){
        RedemptionEntity redemption = new RedemptionEntity();
        CustomerEntity customer = this.customerEntityFacade.find(customerId);
        redemption.setCustomer(customer);
        redemption.setDateOfRedemption(dateofRedemption);
        this.redemptionEntityFacade.create(redemption);
        return redemption;
    }

    public RedemptionEntity addRedemptionLine(Long redemptionId, Long lineId){
        RedemptionEntity redemption = this.redemptionEntityFacade.find(redemptionId);
        RedemptionLineEntity line = this.redemptionLineEntityFacade.find(lineId);
        Set<RedemptionLineEntity> lines = redemption.getRedemptionlines();
        lines.add(line);
        this.redemptionEntityFacade.edit(redemption);
        return redemption;
    }

    public RedemptionLineEntity createRedemptionLine(int quantity, Long rewardId){
        RewardTypeEntity reward = this.rewardTypeEntityFacade.find(rewardId);
        RedemptionLineEntity line = new RedemptionLineEntity();
        line.setQuantity(quantity);
        line.setRewardType(reward);
        this.redemptionLineEntityFacade.create(line);
        return line;
    }

    public void deleteRedemption(Long id) throws ExistException{
        RedemptionEntity redemption = this.redemptionEntityFacade.find(id);
        if(redemption == null){
            throw new ExistException("Redemption doesnt exist");
        }
        this.redemptionEntityFacade.remove(redemption);
    }
    
}
