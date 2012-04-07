/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.MerchantEntity;
import entities.RewardTypeEntity;
import exceptions.ExistException;
import facades.MerchantEntityFacadeLocal;
import facades.RewardTypeEntityFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author zen9
 */
@Stateful
public class RewardManagerBean implements RewardManagerRemote {
    @EJB
    private RewardTypeEntityFacadeLocal rewardTypeEntityFacade;
    @EJB
    private MerchantEntityFacadeLocal merchantEntityFacade;

    public RewardTypeEntity addRewardType(String name, int points) {
        RewardTypeEntity reward = new RewardTypeEntity();
        reward.create(name, points);
        this.rewardTypeEntityFacade.create(reward);
        return reward;
    }

    /**
     * since we do not cascade persist, only the reward types will be deleted,
     * merchants associated are left intact
     * @param id
     */
    public void deleteRewardType(Long id) throws ExistException{
        RewardTypeEntity reward = this.rewardTypeEntityFacade.find(id);
        if(reward == null) {
            throw new ExistException("Reward type doesnt exist");
        }
        this.rewardTypeEntityFacade.remove(reward);
    }

    public void associateMerchant(Long id, String merchantId) throws ExistException{
        RewardTypeEntity reward = this.rewardTypeEntityFacade.find(id);
        MerchantEntity merchant = this.merchantEntityFacade.find(merchantId);
        if(reward == null){
            throw new ExistException("reward does not exists");
        }
        if(merchant == null){
            throw new ExistException("merchant does not exists");
        }
        List<MerchantEntity> merchants = reward.getMerchants();
        List<RewardTypeEntity> rewards = merchant.getRewardTypes();
        merchants.add(merchant);
        rewards.add(reward);
        this.merchantEntityFacade.edit(merchant);
        this.rewardTypeEntityFacade.edit(reward);
    }


}
