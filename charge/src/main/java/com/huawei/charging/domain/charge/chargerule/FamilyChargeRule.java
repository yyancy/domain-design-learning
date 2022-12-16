package com.huawei.charging.domain.charge.chargerule;

import com.huawei.charging.domain.charge.ChargeContext;
import com.huawei.charging.domain.charge.ChargeRecord;
import com.huawei.charging.domain.charge.Money;
import com.huawei.charging.domain.charge.chargeplan.ChargePlanType;
import com.huawei.charging.domain.charge.chargeplan.FamilyChargePlan;
import com.huawei.charging.domain.charge.chargeplan.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class FamilyChargeRule extends AbstractChargeRule {

    private FamilyMember familyMember = new FamilyMember();

    @Override
    public ChargeRecord doCharge(ChargeContext ctx) {
        if (familyMember.isMember(ctx.otherSidePhoneNo)) {
            log.debug("Family Charge plan for Account : " + ctx.account);
            ChargeRecord chargeRecord = new ChargeRecord(ctx.phoneNo, ctx.callType, ctx.durationToCharge,
                    ChargePlanType.FAMILY, Money.of(0));
            ctx.setDurationToCharge(0);
            return chargeRecord;
        }
        return null;
    }

    public static class FamilyMember implements Resource {
        private Set<Long> familyMembers = new HashSet<>();

        /**
         * Mock here, 真实场景，情亲号码肯定也是从外系统获取的
         */
        public FamilyMember() {
            familyMembers.add(13681874561L);
            familyMembers.add(15921582125L);
        }

        public boolean isMember(long phoneNo) {
            return familyMembers.contains(phoneNo);
        }
    }
}
