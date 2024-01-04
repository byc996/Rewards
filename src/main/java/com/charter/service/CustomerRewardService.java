package com.charter.service;

import java.util.List;
import java.util.Map;

public interface CustomerRewardService {

    Map<Integer, Integer> getMonthlyRewardByCustomerId(long customerId);
    int getTotalRewardByCustomerId(long customerId);
}
