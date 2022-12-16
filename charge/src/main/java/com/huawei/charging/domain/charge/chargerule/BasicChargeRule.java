package com.huawei.charging.domain.charge.chargerule;

import com.huawei.charging.domain.charge.*;
import com.huawei.charging.domain.charge.chargeplan.BasicChargePlan;
import com.huawei.charging.domain.charge.chargeplan.ChargePlanType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BasicChargeRule extends AbstractChargeRule {

    /**
     * 主叫单价。单位是角，5表示0.5元每分钟
     */
    public final int CALLING_PRICE = 5;

    /**
     * 主叫单价。单位是角，4表示0.4元每分钟
     */
    public final int CALLED_PRICE = 4;

    @Override
    public ChargeRecord doCharge(ChargeContext ctx) {
        if (!ctx.needCharge()) {
            log.debug("No need charge for : " + ctx);
            return null;
        }
        Money cost;
        int duration = ctx.durationToCharge;
        if (ctx.callType == CallType.CALLING) {
            cost = Money.of(duration * CALLING_PRICE);
        } else {
            cost = Money.of(duration * CALLED_PRICE);
        }
        ChargeRecord chargeRecord = new ChargeRecord(ctx.phoneNo, ctx.callType, duration, ChargePlanType.BASIC, cost);

        //在账号上扣减费用
        ctx.account.getRemaining().minus(cost);
        ctx.setDurationToCharge(0);
        return chargeRecord;
    }
}
