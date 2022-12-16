package com.huawei.charging.domain.charge.chargeplan;

import com.huawei.charging.domain.charge.chargerule.ChargeRule;
import com.huawei.charging.domain.charge.chargerule.FamilyChargeRule;

import java.util.HashSet;
import java.util.Set;

public class FamilyChargePlan extends ChargePlan<FamilyChargeRule.FamilyMember> {

    public FamilyChargePlan(ChargeRule rule) {
        this.priority = 2;
        super.chargeRule = rule;
    }

    @Override
    public FamilyChargeRule.FamilyMember getResource() {
        return new FamilyChargeRule.FamilyMember();
    }

    @Override
    public ChargePlanType getType() {
        return ChargePlanType.FAMILY;
    }

}

