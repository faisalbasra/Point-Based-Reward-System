/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.RewardTypeEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface RewardTypeEntityFacadeRemote {

    void create(RewardTypeEntity rewardTypeEntity);

    void edit(RewardTypeEntity rewardTypeEntity);

    void remove(RewardTypeEntity rewardTypeEntity);

    RewardTypeEntity find(Object id);

    List<RewardTypeEntity> findAll();

}
