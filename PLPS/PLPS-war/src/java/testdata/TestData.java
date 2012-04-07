/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testdata;

import entities.RewardTypeEntity;
import exceptions.ExistException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import managers.CustomerManagerRemote;
import managers.MerchantManagerRemote;
import managers.RewardManagerRemote;

/**
 *
 * @author zen9
 */
public class TestData {

    private CustomerManagerRemote customerManagerBean;
    private MerchantManagerRemote merchantManagerBean;
    private RewardManagerRemote rewardManagerBean;

    public TestData(){
        this.customerManagerBean = this.lookupCustomerManagerBean();
        this.merchantManagerBean = this.lookupMerchantManagerBean();
        this.rewardManagerBean = this.lookupRewardManagerBean();
    }

    public void addCustomer(){
        try {
            this.customerManagerBean.createCustomer("1", "wei wei", "asdasd", "randomtone");
            this.customerManagerBean.createCustomer("2", "wei32ei", "asdasd", "randomtone");
            this.customerManagerBean.createCustomer("3", "wei 3ei", "asdasd", "randomtone");
            this.customerManagerBean.createCustomer("4", "wei23wei", "asdasd", "randomtone");
            this.customerManagerBean.createCustomer("5", "wei 2wei", "asdasd", "randomtone");
            this.customerManagerBean.createCustomer("6", "wei w2ei", "asdasd", "randomtone");
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMerchants(){
        try {
            String name = "Merchant";
            for(int i=0; i<30; i++){
                this.merchantManagerBean.createMerchant(String.valueOf(i), name+String.valueOf(i), "merchant address");
            }
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRewardType(){
        try {
            RewardTypeEntity reward1 = this.rewardManagerBean.addRewardType("SushiVoucher", 100);
            RewardTypeEntity reward2 = this.rewardManagerBean.addRewardType("Swimming offer", 200);
            RewardTypeEntity reward3 = this.rewardManagerBean.addRewardType("50% discount", 300);
            RewardTypeEntity reward4 = this.rewardManagerBean.addRewardType("10% off dinner", 400);
            RewardTypeEntity reward5 = this.rewardManagerBean.addRewardType("Buffet all day", 500);
            this.rewardManagerBean.associateMerchant(reward1.getId(),"1");
            this.rewardManagerBean.associateMerchant(reward1.getId(),"2");
            this.rewardManagerBean.associateMerchant(reward1.getId(),"3");
            this.rewardManagerBean.associateMerchant(reward2.getId(),"1");
            this.rewardManagerBean.associateMerchant(reward2.getId(),"2");
            this.rewardManagerBean.associateMerchant(reward2.getId(),"3");
            this.rewardManagerBean.associateMerchant(reward2.getId(),"4");
            this.rewardManagerBean.associateMerchant(reward2.getId(),"5");
            this.rewardManagerBean.associateMerchant(reward3.getId(),"6");
            this.rewardManagerBean.associateMerchant(reward3.getId(),"7");
            this.rewardManagerBean.associateMerchant(reward4.getId(),"8");
            this.rewardManagerBean.associateMerchant(reward4.getId(),"9");
            this.rewardManagerBean.associateMerchant(reward4.getId(),"10");
            this.rewardManagerBean.associateMerchant(reward4.getId(),"11");
            this.rewardManagerBean.associateMerchant(reward5.getId(),"1");  
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private CustomerManagerRemote lookupCustomerManagerBean() {
        try {
            Context c = new InitialContext();
            return (CustomerManagerRemote) c.lookup("java:comp/env/CustomerManagerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MerchantManagerRemote lookupMerchantManagerBean() {
        try {
            Context c = new InitialContext();
            return (MerchantManagerRemote) c.lookup("java:comp/env/MerchantManagerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RewardManagerRemote lookupRewardManagerBean() {
        try {
            Context c = new InitialContext();
            return (RewardManagerRemote) c.lookup("java:comp/env/RewardManagerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
