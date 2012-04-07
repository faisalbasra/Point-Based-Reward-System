package plpsappclient;

import entities.ClaimEntity;
import entities.CustomerEntity;
import entities.MerchantEntity;
import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import entities.RewardTypeEntity;
import java.util.List;

public class DbDisplay {
    
    public static void showRedemptions(){
        List<RedemptionEntity> redemptions = Main.redemptionEntityFacade.findAll();
        for(RedemptionEntity r:redemptions){
            System.out.println(r);
        }
    }

    public static void showRedemptionLines(){
        List<RedemptionLineEntity> redemptionlines = Main.redemptionLineEntityFacade.findAll();
        for(RedemptionLineEntity r:redemptionlines){
            System.out.print(r.toString());
            System.out.print(",rewardId:"+r.getRewardType().getId());
            System.out.println(",rewardName:"+r.getRewardType().getName());
        }
    }

    public static void showMerchants(){
        List<MerchantEntity> merchants = Main.merchantEntityFacade.findAll();
        for(MerchantEntity m:merchants){
            System.out.println(m.toString()+",Name:"+m.getName());
        }
    }

    public static void showCustomers(){
        List<CustomerEntity> customers = Main.customerEntityFacade.findAll();
        for(CustomerEntity c:customers){
            System.out.println(c.toString()+",name:"+c.getName());
        }
    }

    public static void showRewardTypes(){
        List<RewardTypeEntity> rewards = Main.rewardTypeEntityFacade.findAll();
        for(RewardTypeEntity r:rewards){
            System.out.print("rewardId:"+r.getId());
            System.out.println(",rewardName:"+r.getName());
        }
    }

    public static void showClaims(){
        List<ClaimEntity> claims = Main.claimEntityFacade.findAll();
        for(ClaimEntity c:claims){
            System.out.println(c);
        }
    }

}
