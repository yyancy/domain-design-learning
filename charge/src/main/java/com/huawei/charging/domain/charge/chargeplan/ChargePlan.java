package com.huawei.charging.domain.charge.chargeplan;

import com.huawei.charging.domain.charge.chargerule.ChargeRule;

public abstract class ChargePlan<T extends Resource> implements Comparable<ChargePlan> {

    protected int priority;

    protected ChargeRule chargeRule;

    public abstract T getResource();

    public abstract ChargePlanType getType();

    public ChargeRule getChargeRule() {
        return this.chargeRule;
    }

    /**
     * 不同套餐之间的优先级关系
     *
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(ChargePlan other) {
        return other.priority - this.priority;
    }

    @Override
    public String toString() {
        return "ChargePlan{chargeType=" + getType() +
                ", priority=" + priority +
                '}';
    }
}
