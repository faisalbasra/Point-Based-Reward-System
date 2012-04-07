/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author zen9
 */
@Entity(name = "Merchant")
public class MerchantEntity implements Serializable {
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<RewardTypeEntity> rewardTypes;

    @Id
    private String id;
    private String name;
    private String address;

    public void create(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RewardTypeEntity> getRewardTypes() {
        return rewardTypes;
    }

    public void setRewardTypes(List<RewardTypeEntity> rewardTypes) {
        this.rewardTypes = rewardTypes;
    }

    @Override
    public String toString() {
        return "entities.MerchantEntity[id=" + id + "]";
    }
}
