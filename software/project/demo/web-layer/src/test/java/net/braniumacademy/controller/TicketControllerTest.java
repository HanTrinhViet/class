package net.braniumacademy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.braniumacademy.dto.TicketDTO;
import net.braniumacademy.exception.AgentNotFoundException;
import net.braniumacademy.exception.InvalidTicketStateException;
import net.braniumacademy.exception.TicketNotFoundException;
import net.braniumacademy.model.Status;
import net.braniumacademy.service.TicketService;
import net.braniumacademy.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private TicketService ticketService;


    @Autowired
    public TicketControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//        mockMvc.perform(post("/tickets").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }

    @Test
    void givenTicketDetails_WhenTicketIsCreated_ThenTicketIsSaved() throws Exception {
        String ticketDescription = "Sample ticket description";
        TicketDTO ticketDTO = new TicketDTO(null, ticketDescription, Status.NEW,
                null, null, null, null);
        when(ticketService.createTicket(any(TicketDTO.class))).thenReturn(ticketDTO);
        mockMvc.perform(post("/tickets").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is(ticketDescription)))
                .andExpect(jsonPath("$.status", is(Status.NEW.name())))
                .andDo(print());
    }

    @Test
    void givenNewTicket_WhenAssigningAgent_thenStatusIsInProgress() throws Exception {
        Long ticketId = 1L;
        Long agentId = 1L;
        String agentName = "Agent101";
        String ticketDescription = "Description";
        TicketDTO ticketDTO = new TicketDTO(ticketId, ticketDescription,
                Status.IN_PROGRESS, null, null, agentName, null);
        when(ticketService.assignAgentToTicket(ticketId, agentId)).thenReturn(ticketDTO);
        mockMvc.perform(put("/tickets/{id}/agent/{agentId}", ticketId, agentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(Status.IN_PROGRESS.name())))
                .andExpect(jsonPath("$.assignedAgent", is(agentName)));
    }

    @Test
    void givenTicketNotInNewState_whenAssigningAgent_thenThrowException() throws Exception {
        Long ticketId = 1L;
        Long agentId = 1L;
        when(ticketService.assignAgentToTicket(ticketId, agentId))
                .thenThrow(new InvalidTicketStateException(Constants.ONLY_NEW_TICKETS_CAN_BE_ASSIGNED_TO_AN_AGENT));
        mockMvc.perform(put("/tickets/{id}/agent/{agentId}", ticketId, agentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Constants.ONLY_NEW_TICKETS_CAN_BE_ASSIGNED_TO_AN_AGENT));

    }

    @Test
    void givenNonexistentAgent_whenAssigningToTicket_thenThrowException() throws Exception {
        Long ticketId = 1L;
        Long nonExistentAgentId = 1L;
        when(ticketService.assignAgentToTicket(ticketId, nonExistentAgentId))
                .thenThrow(new AgentNotFoundException(Constants.AGENT_NOT_FOUND));
        mockMvc.perform(put("/tickets/{id}/agent/{agentIdentifier}", ticketId, nonExistentAgentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Constants.AGENT_NOT_FOUND));
    }

    @Test
    void givenNonexistentTicket_whenAssigningAgent_thenThrowException() throws Exception {
        Long nonExistentTicketId = 1L;
        Long agentId = 1L;
        when(ticketService.assignAgentToTicket(nonExistentTicketId, agentId))
                .thenThrow(new TicketNotFoundException(Constants.TICKET_NOT_FOUND));
        mockMvc.perform(put("/tickets/{ticketIdentifier}/agent/{agentId}", nonExistentTicketId, agentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Constants.TICKET_NOT_FOUND));
    }




}
