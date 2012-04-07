/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author zen9
 */
@Entity(name = "Customer")
public class CustomerEntity implements Serializable {

    @Id
    private String id;
    private String name;
    private String password;
    private int points;
    @OneToOne(cascade = {CascadeType.ALL})
    private ContactEntity contact;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer")
    private Set<RedemptionEntity> redemptions;

    public void create(String id, String name, String password, ContactEntity contact) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.points = 500;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public Set<RedemptionEntity> getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(Set<RedemptionEntity> redemptions) {
        this.redemptions = redemptions;
    }

    @Override
    public String toString() {
        return "entities.CustomerEntity[id=" + id + "]";
    }
}
