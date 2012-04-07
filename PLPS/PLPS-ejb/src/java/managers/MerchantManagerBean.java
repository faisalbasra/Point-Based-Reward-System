/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.MerchantEntity;
import entities.RewardTypeEntity;
import exceptions.DeleteException;
import exceptions.ExistException;
import facades.MerchantEntityFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author zen9
 */
@Stateful
public class MerchantManagerBean implements MerchantManagerRemote {

    @EJB
    private MerchantEntityFacadeLocal merchantEntityFacade;

    public void createMerchant(String id, String name, String address) throws ExistException// </editor-fold>
    {
        if (this.merchantEntityFacade.find(id) != null) {
            throw new ExistException("Merchant exists, id is "+id);
        }
        MerchantEntity merchant = new MerchantEntity();
        merchant.create(id, name, address);
        this.merchantEntityFacade.create(merchant);
    }

    public void deleteMerchant(String id) throws DeleteException {
        MerchantEntity merchant = this.merchantEntityFacade.find(id);
        if (merchant == null) {
            throw new DeleteException("Merchant does not exists");
        }
        List<RewardTypeEntity> rewards = merchant.getRewardTypes();
        if (!rewards.isEmpty()) {
            throw new DeleteException("Merchant has reward types associated");
        }
        this.merchantEntityFacade.remove(merchant);
    }
}
