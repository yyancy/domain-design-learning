package com.huawei.charging.domain.charge.chargeplan;

import com.huawei.charging.domain.charge.ChargeContext;
import com.huawei.charging.domain.charge.ChargeRecord;
import com.huawei.charging.domain.charge.chargerule.ChargeRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dongyang
 * @since 2022-12-16
 */
public class CompositeChargePlan {
    public List<ChargePlan<?>> chargePlans;

    public CompositeChargePlan(List<ChargePlan<?>> chargePlans) {
        this.chargePlans = chargePlans;
    }

    public List<ChargeRecord> doCharge(ChargeContext chargeContext) {
        List<ChargeRecord> chargeRecords = new ArrayList<>();
        for (ChargePlan<?> chargePlan : chargePlans) {
            ChargeRule chargeRule = chargePlan.getChargeRule();
            ChargeRecord chargeRecord = chargeRule.doCharge(chargeContext);
            if (chargeRecord != null) {
                chargeRecord.setSessionId(chargeContext.getSession().getSessionId());
                //fill fields for persistence needs
                chargeRecord.setCreateTime(new Date());
                chargeRecord.setUpdateTime(new Date());
                chargeRecords.add(chargeRecord);
            }
        }
        return chargeRecords;
    }
}
