package com.charter.controller;

import com.charter.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reward")
public class RewardController {

    private RewardService customerRewardService;

    public RewardController(RewardService customerRewardService) {
        this.customerRewardService = customerRewardService;
    }

    /**
     * Get monthly reward during three month points by customer id
     * @param customerId
     * @return
     */
    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyRewardByCustomerId(@PathVariable Long customerId) {
        Map<Integer, Integer> monthlyReward = customerRewardService.getMonthlyRewardByCustomerId(customerId);
        return ResponseEntity.ok(monthlyReward);
    }

    /**
     * Get total reward points during three month by customer id
     * @param customerId
     * @return
     */
    @GetMapping("/total/{customerId}")
    public ResponseEntity<Integer> getTotalRewardByCustomerId(@PathVariable Long customerId) {
        int totalReward = customerRewardService.getTotalRewardByCustomerId(customerId);
        return ResponseEntity.ok(totalReward);
    }

}
