/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ClaimEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface ClaimEntityFacadeRemote {

    void create(ClaimEntity claimEntity);

    void edit(ClaimEntity claimEntity);

    void remove(ClaimEntity claimEntity);

    ClaimEntity find(Object id);

    List<ClaimEntity> findAll();

}
