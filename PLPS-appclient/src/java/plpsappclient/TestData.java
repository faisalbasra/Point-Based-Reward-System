/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plpsappclient;

import entities.RewardTypeEntity;
import exceptions.ExistException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zen9
 */
public class TestData {

    public static void addCustomer(){
        try {
            Main.customerManagerBean.createCustomer("1", "wei wei", "asdasd", "randomtone");
            Main.customerManagerBean.createCustomer("2", "wei32ei", "asdasd", "randomtone");
            Main.customerManagerBean.createCustomer("3", "wei 3ei", "asdasd", "randomtone");
            Main.customerManagerBean.createCustomer("4", "wei23wei", "asdasd", "randomtone");
            Main.customerManagerBean.createCustomer("5", "wei 2wei", "asdasd", "randomtone");
            Main.customerManagerBean.createCustomer("6", "wei w2ei", "asdasd", "randomtone");
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addMerchants(){
        try {
            String name = "Merchant";
            for(int i=0; i<30; i++){
                Main.merchantManagerBean.createMerchant(String.valueOf(i), name+String.valueOf(i), "merchant address");
            }
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addRewardType(){
        try {
            RewardTypeEntity reward1 = Main.rewardManagerBean.addRewardType("SushiVoucher", 100);
            RewardTypeEntity reward2 = Main.rewardManagerBean.addRewardType("Swimming offer", 200);
            RewardTypeEntity reward3 = Main.rewardManagerBean.addRewardType("50% discount", 300);
            RewardTypeEntity reward4 = Main.rewardManagerBean.addRewardType("10% off dinner", 400);
            RewardTypeEntity reward5 = Main.rewardManagerBean.addRewardType("Buffet all day", 500);
            Main.rewardManagerBean.associateMerchant(reward1.getId(),"1");
            Main.rewardManagerBean.associateMerchant(reward1.getId(),"2");
            Main.rewardManagerBean.associateMerchant(reward1.getId(),"3");
            Main.rewardManagerBean.associateMerchant(reward2.getId(),"1");
            Main.rewardManagerBean.associateMerchant(reward2.getId(),"2");
            Main.rewardManagerBean.associateMerchant(reward2.getId(),"3");
            Main.rewardManagerBean.associateMerchant(reward2.getId(),"4");
            Main.rewardManagerBean.associateMerchant(reward2.getId(),"5");
            Main.rewardManagerBean.associateMerchant(reward3.getId(),"6");
            Main.rewardManagerBean.associateMerchant(reward3.getId(),"7");
            Main.rewardManagerBean.associateMerchant(reward4.getId(),"8");
            Main.rewardManagerBean.associateMerchant(reward4.getId(),"9");
            Main.rewardManagerBean.associateMerchant(reward4.getId(),"10");
            Main.rewardManagerBean.associateMerchant(reward4.getId(),"11");
            Main.rewardManagerBean.associateMerchant(reward5.getId(),"1");
        } catch (ExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
