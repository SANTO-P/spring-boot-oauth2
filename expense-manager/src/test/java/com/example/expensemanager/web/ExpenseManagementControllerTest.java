package com.example.expensemanager.web;

import com.example.expensemanager.repository.ExpenseManagementRepository;
import com.example.expensemanager.service.ExpenseManagementService;
import com.example.expensemanager.view.ExpenseView;
import com.example.expensemanager.vo.RoleInformation;
import com.example.expensemanager.web.request.ExpenseRequest;
import com.example.expensemanager.web.request.ExpenseUpdationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.expensemanager.vo.ExpenseState.REJECTED;
import static com.example.expensemanager.vo.ReasonCode.INSUFFICIENT_FUNDS;
import static org.junit.Assert.*;

import static com.example.expensemanager.vo.RoleType.ADMIN;
import static com.example.expensemanager.vo.RoleType.BOOKKEPEER;
import static com.example.expensemanager.vo.Vendor.ABC;
import static java.math.BigDecimal.valueOf;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseManagementControllerTest {

    private ExpenseManagementService service;
    @Mock
    private ExpenseManagementRepository repository;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        service = new ExpenseManagementService(repository);
        mockMvc = standaloneSetup(new ExpenseManagementController(service)).build();
        objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldCreateExpenseSuccessfullyByAdmin() throws Exception {
        ExpenseRequest request = new ExpenseRequest("invoice-number", ABC, valueOf(200), new Date(),
                new RoleInformation(ADMIN, null));
        ExpenseView view = new ExpenseView(request.getInvoiceNumber(), request.getVendor(), request.getAmount(),
                request.getInvoiceDate(),null, null, null,
                request.getRoleInformation().getRoleType(),request.getRoleInformation().getRoleId());

        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/expenses")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(rs -> {
            Mockito.verify(repository).save(view);
        });


    }

    @Test
    public void shouldCreateExpenseSuccessfullyByBookkeeper() throws Exception {
        ExpenseRequest request = new ExpenseRequest("invoice-number", ABC, valueOf(200), new Date(),
                new RoleInformation(BOOKKEPEER, "role-id-1"));
        ExpenseView view = new ExpenseView(request.getInvoiceNumber(), request.getVendor(), request.getAmount(),
                request.getInvoiceDate(),null, null, null,
                request.getRoleInformation().getRoleType(),request.getRoleInformation().getRoleId());

        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/expenses")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(rs -> {
                    Mockito.verify(repository).save(view);
                });
    }

    @Test
    public void shouldRetrieveExpensesCreatedByBookkeeper() throws Exception {
        String roleId = "role-id";
        ExpenseView view = new ExpenseView("invoice-number", ABC, valueOf(200),
                new Date(),null, null, null,
                BOOKKEPEER, roleId);
        List<ExpenseView> viewList = new ArrayList<>();
        viewList.add(view);

        when(repository.findByRoleTypeAndRoleId(BOOKKEPEER, roleId)).thenReturn(Optional.of(viewList));

        mockMvc.perform(
                get("/expenses/{roldeId}", roleId)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(rs -> {
                     assertEquals(rs.getResponse().getContentAsString(), objectMapper.writeValueAsString(viewList));
                });
    }

    @Test
    public void shouldRetrieveExpensesCreatedByAdmin() throws Exception {
        ExpenseView view1 = new ExpenseView("invoice-number-1", ABC, valueOf(200),
                new Date(),null, null, null,
                ADMIN, null);
        ExpenseView view2 = new ExpenseView("invoice-number-2", ABC, valueOf(200),
                new Date(),null, null, null,
                ADMIN, null);
        List<ExpenseView> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);

        when(repository.findByRoleType(ADMIN)).thenReturn(Optional.of(viewList));

        mockMvc.perform(
                get("/expenses/")
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(rs -> {
                    assertEquals(rs.getResponse().getContentAsString(), objectMapper.writeValueAsString(viewList));
                });
    }

    @Test
    public void shouldUpdateExpenseStatusByAdmin() throws Exception {
        String invoiceNumber = "invoice-number-1";
        ExpenseUpdationRequest request = new ExpenseUpdationRequest(REJECTED, INSUFFICIENT_FUNDS);
        ExpenseView view = new ExpenseView(invoiceNumber, ABC, valueOf(200),
                new Date(),null, null, null,
                ADMIN, null);

        String requestBody = objectMapper.writeValueAsString(request);
        when(repository.findById(invoiceNumber)).thenReturn(Optional.of(view));
        view.setExpenseState(request.getExpenseState());
        view.setStatusUpdateTimeStamp(new Date());
        view.setReasonCode(request.getReasonCode());

        mockMvc.perform(
                put("/expenses/{invoiceNumber}", invoiceNumber)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(rs -> {
                    Mockito.verify(repository).findById(invoiceNumber);
                    Mockito.verify(repository).save(view);
                });
    }
}