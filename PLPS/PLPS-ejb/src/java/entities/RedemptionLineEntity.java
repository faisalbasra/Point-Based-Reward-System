/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author zen9
 */
@Entity (name = "RedemptionLine")
public class RedemptionLineEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private RewardTypeEntity rewardType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RewardTypeEntity getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardTypeEntity rewardType) {
        this.rewardType = rewardType;
    }

    @Override
    public String toString() {
        return "entities.RedemptionLineEntity[id=" + id + "]";
    }
}
