package com.charter.controller;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDto;
import com.charter.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testCreateTransaction() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionDto transactionDto = TransactionDto.builder().amount(78).customerId(1L).build();
//        given(transactionService.createTransaction(ArgumentMatchers.any()))
//                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(transactionService.createTransaction(Mockito.any(TransactionDto.class))).thenReturn(transactionDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto)));

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",
                        CoreMatchers.is(transactionDto.getAmount())))
                .andDo(MockMvcResultHandlers.print());

    }
}