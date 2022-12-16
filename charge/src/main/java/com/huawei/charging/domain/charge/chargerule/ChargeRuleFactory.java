package com.huawei.charging.domain.charge.chargerule;

import com.huawei.charging.domain.charge.chargeplan.ChargePlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChargeRuleFactory {
//    public static CompositeChargePlan get(List<ChargePlan> chargePlanList) {
//        CompositeChargePlan compositeChargePlan = new CompositeChargePlan();
//        compositeChargePlan.chargePlans = chargePlanList;
//        return compositeChargePlan;
//    }

    public static CompositeChargeRule get2(List<ChargePlan> chargePlanList) {
        //按套餐的优先级进行排序
        Collections.sort(chargePlanList);

        List<ChargeRule> chargeRules = new ArrayList<>();
        for (ChargePlan<?> chargePlan : chargePlanList) {
            //            chargeRules.add(chargeRule);
            ChargeRule ruler = chargePlan.getChargeRule();
            ruler.belongsTo(chargePlan);
            chargeRules.add(ruler);
        }
        CompositeChargeRule compositeChargeRule = new CompositeChargeRule();
        compositeChargeRule.chargeRules = chargeRules;
        return compositeChargeRule;
    }
}
