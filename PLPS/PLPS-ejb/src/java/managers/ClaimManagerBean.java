/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import entities.ClaimEntity;
import entities.RedemptionEntity;
import exceptions.ExistException;
import facades.ClaimEntityFacadeLocal;
import facades.RedemptionEntityFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author zen9
 */
@Stateful
public class ClaimManagerBean implements ClaimManagerRemote {
    @EJB
    private RedemptionEntityFacadeLocal redemptionEntityFacade;
    @EJB
    private ClaimEntityFacadeLocal claimEntityFacade;
    
    public void claimRedemption(Long redemptionId, Date dateOfClaim) throws ExistException{
        ClaimEntity claim = new ClaimEntity();
        claim.setDateOfClaim(dateOfClaim);
        RedemptionEntity redemption = this.redemptionEntityFacade.find(redemptionId);
        if(redemption == null){
            throw new ExistException("redemption does not exist");
        }
        claim.setRedemption(redemption);
        this.claimEntityFacade.create(claim);
    }

}
