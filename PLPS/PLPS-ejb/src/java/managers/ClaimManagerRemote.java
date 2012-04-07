/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import exceptions.ExistException;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface ClaimManagerRemote {

    public void claimRedemption(Long redemptionId, Date dateOfClaim) throws ExistException;
    
}
