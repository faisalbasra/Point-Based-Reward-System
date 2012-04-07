/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plpsappclient;

import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import entities.RewardTypeEntity;
import exceptions.DeleteException;
import exceptions.ExistException;
import facades.ClaimEntityFacadeRemote;
import facades.ContactEntityFacadeRemote;
import facades.CustomerEntityFacadeRemote;
import facades.MerchantEntityFacadeRemote;
import facades.RedemptionEntityFacadeRemote;
import facades.RedemptionLineEntityFacadeRemote;
import facades.RewardTypeEntityFacadeRemote;
import java.util.Date;
import javax.ejb.EJB;
import managers.ClaimManagerRemote;
import managers.CustomerManagerRemote;
import managers.MerchantManagerRemote;
import managers.RedemptionManagerRemote;
import managers.RewardManagerRemote;

/**
 *
 * @author zen9
 */
public class Main {
    @EJB
    public static MerchantEntityFacadeRemote merchantEntityFacade;
    @EJB
    public static RewardTypeEntityFacadeRemote rewardTypeEntityFacade;
    @EJB
    public static CustomerEntityFacadeRemote customerEntityFacade;
    @EJB
    public static ContactEntityFacadeRemote contactEntityFacade;
    @EJB
    public static ClaimEntityFacadeRemote claimEntityFacade;
    @EJB
    public static RedemptionLineEntityFacadeRemote redemptionLineEntityFacade;
    @EJB
    public static RedemptionEntityFacadeRemote redemptionEntityFacade;
    @EJB
    public static RedemptionManagerRemote redemptionManagerBean;
    @EJB
    public static ClaimManagerRemote claimManagerBean;
    @EJB
    public static RewardManagerRemote rewardManagerBean;
    @EJB
    public static MerchantManagerRemote merchantManagerBean;
    @EJB
    public static CustomerManagerRemote customerManagerBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
        System.out.println(customerManagerBean);
        TestData.addCustomer();
        TestData.addMerchants();
        TestData.addRewardType();
        **/
        while(true){
            mainScreen();
        }
    }

    static void mainScreen() {
        System.out.println("\n\nWelcome to Point Based Loyalty System Client\n");
        System.out.println("Please enter your options");
        System.out.println("1: Add customer");
        System.out.println("2: Delete customer");
        System.out.println("3: Add merchant");
        System.out.println("4: Delete merchant");
        System.out.println("5: Add Reward Type");
        System.out.println("6: Delete Reward Type");
        System.out.println("7: Associate reward type with merchant");
        System.out.println("8: Add a redemption");
        System.out.println("9: Claim redemption");
        int choice = Console.getInt("choice");
        switch (choice) {
            case 1:
                System.out.println("Enter a customer detail");
                Main.doAddCustomer();
                break;
            case 2:
                System.out.println("Provide a customer id to delete");
                Main.doDeleteCustomer();
                break;
            case 3:
                System.out.println("Enter a merchant detail");
                Main.doAddMerchant();
                break;
            case 4:
                System.out.println("Enter a merchant id to delete");
                Main.doDeleteMerchant();
                break;
            case 5:
                System.out.println("Add reward type");
                Main.doAddRewardType();
                break;
            case 6:
                System.out.println("Delete reward type");
                Main.doDeleteRewardType();
                break;
            case 7:
                System.out.println("Associate reward type");
                Main.doAssocRewardType();
                break;
            case 8:
                System.out.println("Add a redemption");
                Main.doAddRedemption();
                break;
            case 9:
                System.out.println("Claim a redemption");
                Main.doClaimRedemption();
                break;
            default:
                System.out.println("You have entered an invalid option");
        }
    }

    static void doAddCustomer() {
        String id = Console.getString("id", null);
        String name = Console.getString("name", null);
        String password = Console.getString("password", null);
        String address = Console.getString("address", null);
        try {
            Main.customerManagerBean.createCustomer(id, name, password, address);
            System.out.println("customer added.");
        } catch (ExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void doDeleteCustomer() {
        DbDisplay.showCustomers();
        String id = Console.getString("id", null);
        try {
            Main.customerManagerBean.deleteCustomer(id);
            System.out.println("customer deleted.");
        } catch (DeleteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void doAddMerchant() {
        String id = Console.getString("id", null);
        String name = Console.getString("name", null);
        String address = Console.getString("address", null);
        try {
            Main.merchantManagerBean.createMerchant(id, name, address);
            System.out.println("merchant added.");
        } catch (ExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void doDeleteMerchant() {
        DbDisplay.showMerchants();
        String id = Console.getString("id", null);
        try {
            Main.merchantManagerBean.deleteMerchant(id);
            System.out.println("merchant deleted.");
        } catch (DeleteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void doAddRewardType() {
        String name = Console.getString("name", null);
        int points = Console.getInt("points required");
        Main.rewardManagerBean.addRewardType(name, points);
        System.out.println("RewardType added");
    }

    static void doDeleteRewardType() {
        DbDisplay.showRewardTypes();
        Long id = Console.getLong("Id");
        try {
            Main.rewardManagerBean.deleteRewardType(id);
        } catch (ExistException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("RewardType deleted");
    }

    static void doAssocRewardType() {
        System.out.println("Merchants:");
        DbDisplay.showMerchants();
        System.out.println("Reward Types:");
        DbDisplay.showRewardTypes();
        String mid = Console.getString("Merchant Id",null);
        Long rid = Console.getLong("RewardType Id");
        try {
            Main.rewardManagerBean.associateMerchant(rid, mid);
        } catch (ExistException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("associated");
    }

    
    static void doAddRedemption() {
        DbDisplay.showCustomers();
        String customerId = Console.getString("customerId",null);
        Date dateOfRedemption = Console.getDateFromString("date of redemption");
        RedemptionEntity redemption = Main.redemptionManagerBean.createRedemption(customerId, dateOfRedemption);

        Boolean finished = false;
        while(finished == false){
            System.out.println("Please enter a redemption line");
            int quantity = Console.getInt("quantity of the redemption line");
            System.out.println("Please choose from one of the reward type below");
            DbDisplay.showRewardTypes();
            Long id = Console.getLong("reward id");
            RedemptionLineEntity line = Main.redemptionManagerBean.createRedemptionLine(quantity, id);
            Main.redemptionManagerBean.addRedemptionLine(redemption.getId(), line.getId());
            finished = Console.askYesNo("Are you done adding redemption line?");
        }
    }
    
    static void doClaimRedemption() {
        DbDisplay.showRedemptions();
        Long id = Console.getLong("Redemption Id");
        Date dateOfRedemption = Console.getDateFromString("Date of redemption");
        try {
            Main.claimManagerBean.claimRedemption(id, dateOfRedemption);
        } catch (ExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
