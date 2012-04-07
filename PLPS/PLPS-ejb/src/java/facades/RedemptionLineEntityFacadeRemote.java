/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RedemptionLineEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface RedemptionLineEntityFacadeRemote {

    void create(RedemptionLineEntity redemptionLineEntity);

    void edit(RedemptionLineEntity redemptionLineEntity);

    void remove(RedemptionLineEntity redemptionLineEntity);

    RedemptionLineEntity find(Object id);

    List<RedemptionLineEntity> findAll();

}
