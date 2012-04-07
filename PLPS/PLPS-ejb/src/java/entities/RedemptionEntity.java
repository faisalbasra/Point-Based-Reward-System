/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author zen9
 */
@Entity(name = "Redemption")
public class RedemptionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfRedemption;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private CustomerEntity customer;
    
    @OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private ClaimEntity claim;
    
    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Set<RedemptionLineEntity> redemptionlines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimEntity getClaim() {
        return claim;
    }

    public void setClaim(ClaimEntity claim) {
        this.claim = claim;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Date getDateOfRedemption() {
        return dateOfRedemption;
    }

    public void setDateOfRedemption(Date dateOfRedemption) {
        this.dateOfRedemption = dateOfRedemption;
    }

    public Set<RedemptionLineEntity> getRedemptionlines() {
        return redemptionlines;
    }

    public void setRedemptionlines(Set<RedemptionLineEntity> redemptionlines) {
        this.redemptionlines = redemptionlines;
    }

    @Override
    public String toString() {
        return "entities.RedemptionEntity[id=" + id + "]";
    }
}
