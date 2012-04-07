/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RedemptionLineEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zen9
 */
@Local
public interface RedemptionLineEntityFacadeLocal {

    void create(RedemptionLineEntity redemptionLineEntity);

    void edit(RedemptionLineEntity redemptionLineEntity);

    void remove(RedemptionLineEntity redemptionLineEntity);

    RedemptionLineEntity find(Object id);

    List<RedemptionLineEntity> findAll();

}
