/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.MerchantEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zen9
 */
@Remote
public interface MerchantEntityFacadeRemote {

    void create(MerchantEntity merchantEntity);

    void edit(MerchantEntity merchantEntity);

    void remove(MerchantEntity merchantEntity);

    MerchantEntity find(Object id);

    List<MerchantEntity> findAll();

}
