/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RedemptionEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface RedemptionEntityFacadeRemote {

    void create(RedemptionEntity redemptionEntity);

    void edit(RedemptionEntity redemptionEntity);

    void remove(RedemptionEntity redemptionEntity);

    RedemptionEntity find(Object id);

    List<RedemptionEntity> findAll();

}
