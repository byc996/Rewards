package com.charter.service;

import java.util.Map;

public interface RewardService {

    /**
     * Get monthly reward during three month points by customer id
     * @param customerId
     * @return
     */
    Map<Integer, Integer> getMonthlyRewardByCustomerId(long customerId);

    /**
     * Get total reward points during three month by customer id
     * @param customerId
     * @return
     */
    int getTotalRewardByCustomerId(long customerId);
}
