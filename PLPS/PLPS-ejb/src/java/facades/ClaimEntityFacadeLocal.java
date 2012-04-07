/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.ClaimEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zen9
 */
@Local
public interface ClaimEntityFacadeLocal {

    void create(ClaimEntity claimEntity);

    void edit(ClaimEntity claimEntity);

    void remove(ClaimEntity claimEntity);

    ClaimEntity find(Object id);

    List<ClaimEntity> findAll();

}
