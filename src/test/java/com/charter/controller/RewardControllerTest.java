package com.charter.controller;

import com.charter.entity.dto.TransactionDto;
import com.charter.service.RewardService;
import com.charter.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = RewardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetMonthlyRewardByCustomerId() throws Exception {
        Map<Integer, Integer> monthlyReward = new HashMap<>();
        monthlyReward.put(11, 123);
        monthlyReward.put(12, 45);
        when(rewardService.getMonthlyRewardByCustomerId(Mockito.any(Long.class))).thenReturn(monthlyReward);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/reward/monthly/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(monthlyReward)));

        MvcResult mvcResult = resultActions.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Map<Integer, Integer> resultMap = objectMapper.readValue(contentAsString, Map.class);
        assertEquals(monthlyReward.get(11), resultMap.get("11"));
        assertEquals(monthlyReward.get(12), resultMap.get("12"));
    }

    @Test
    void testGetTotalRewardByCustomerId() throws Exception {
        int reward = 12;
        when(rewardService.getTotalRewardByCustomerId(Mockito.any(Long.class))).thenReturn(reward);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/reward/total/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(reward)));

        MvcResult mvcResult = resultActions.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), String.valueOf(reward));
    }
}