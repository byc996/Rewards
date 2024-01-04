package com.charter.controller;

import com.charter.service.CustomerRewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reward")
public class CustomerRewardController {

    private CustomerRewardService customerRewardService;

    public CustomerRewardController(CustomerRewardService customerRewardService) {
        this.customerRewardService = customerRewardService;
    }

    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyRewardByCustomerId(@PathVariable Long customerId) {
        Map<Integer, Integer> monthlyReward = customerRewardService.getMonthlyRewardByCustomerId(customerId);
        return ResponseEntity.ok(monthlyReward);
    }

    @GetMapping("/total/{customerId}")
    public ResponseEntity<Integer> getTotalRewardByCustomerId(@PathVariable Long customerId) {
        int totalReward = customerRewardService.getTotalRewardByCustomerId(customerId);
        return ResponseEntity.ok(totalReward);
    }

}
