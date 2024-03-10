package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.exception.*;
import com.example.demo.model.Agent;
import com.example.demo.model.Status;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private AgentRepository agentRepository;

    @BeforeEach
    void setup() {
        ticketService = new TicketServiceImpl(ticketRepository, agentRepository);
    }

    @Test
    void givenTicketDetails_whenTicketIsCreated_thenCallsRepositorySave() {
        TicketDto ticketDto = new TicketDto(null, "description", null, null, null, null, null);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(new Ticket());

        ticketService.createTicket(ticketDto);

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void givenTicketDetails_whenTicketIsCreated_thenSetsNewStatusAndCreationDate() {
        String description = "description";
        TicketDto ticketDto = new TicketDto(null, description, null, null, null, null, null);

        Ticket savedTicket = new Ticket(1L, description, Status.NEW, LocalDateTime.now());
        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        TicketDto createdTicket = ticketService.createTicket(ticketDto);

        assertNotNull(createdTicket);
        assertEquals(Status.NEW, createdTicket.status());
        assertNotNull(createdTicket.createdDate());
    }

    @Test
    void givenTicketWithoutDescription_whenTicketIsCreated_thenThrowException() {
        TicketDto ticketDto = new TicketDto(null, null, null, null, null, null, null);

        assertThrows(MissingDescriptionException.class, () -> ticketService.createTicket(ticketDto));
    }

    @Test
    void givenNewTicket_whenAssigningAgent_thenStatusIsInProgress() {
        Long ticketId = 1L;
        Long agentId = 1L;
        String description = "description";
        Ticket ticket = new Ticket(ticketId, description, Status.NEW, LocalDateTime.now());
        Agent agent = new Agent(agentId, "Agent001");
        Ticket savedTicket = new Ticket(ticketId, description, Status.IN_PROGRESS, LocalDateTime.now());
        savedTicket.setAssignedAgent(agent);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(agentRepository.findById(agentId)).thenReturn(Optional.of(agent));

        TicketDto updatedTicket = ticketService.assignAgentToTicket(ticketId, agentId);

        assertEquals(ticketId, updatedTicket.id());
        assertEquals(agentId, agent.getId());
        assertEquals(Status.IN_PROGRESS, updatedTicket.status());
    }

    @Test
    void givenNonexistentTicket_whenAssigningAgent_thenThrowException() {
        Long nonExistentTicketId = 999L;
        Long agentId = 1L;

        when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.assignAgentToTicket(nonExistentTicketId, agentId)
        );
    }

    @Test
    void givenNonexistentAgent_whenAssigningToTicket_thenThrowException() {
        Long ticketId = 999L;
        Long nonExistentAgentId = 1L;
        String ticketDescription = "description";
        Ticket ticket = new Ticket(ticketId, ticketDescription, Status.NEW, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(agentRepository.findById(nonExistentAgentId)).thenReturn(Optional.empty());

        assertThrows(AgentNotFoundException.class,
                () -> ticketService.assignAgentToTicket(ticketId, nonExistentAgentId)
        );
    }

    @Test
    void givenTicketNotInNewState_whenAssigningAgent_thenThrowException() {
        Long ticketId = 1L;
        Long agentId = 1L;
        String description = "description";
        Ticket ticket = new Ticket(ticketId, description, Status.IN_PROGRESS, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        assertThrows(InvalidTicketStateException.class,
                () -> ticketService.assignAgentToTicket(ticketId, agentId)
        );
    }

    @Test
    void givenTicketInProgress_whenResolving_thenStatusIsResolved() {
        Long ticketId = 1L;
        String description = "description";
        Ticket ticket = new Ticket(ticketId, description, Status.IN_PROGRESS, LocalDateTime.now());
        Ticket savedTicket = new Ticket(ticketId, description, Status.RESOLVED, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        TicketDto updatedTicket = ticketService.resolveTicket(ticketId);

        assertEquals(Status.RESOLVED, updatedTicket.status());
    }

    @Test
    void givenNonexistentTicket_whenResolving_thenThrowException() {
        Long nonExistentTicketId = 999L;

        when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.resolveTicket(nonExistentTicketId)
        );
    }

    @Test
    void givenTicketNotInProgressState_whenResolving_thenThrowException() {
        Long ticketId = 1L;
        String description = "Description";
        Ticket ticket = new Ticket(ticketId, description, Status.NEW, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        assertThrows(InvalidTicketStateException.class,
                () -> ticketService.resolveTicket(ticketId)
        );
    }

    @Test
    void givenResolvedTicketWithSummary_whenClosing_thenStatusIsClosed() {
        Long ticketId = 1L;
        String description = "description";
        String resolutionSummary = "Summary";
        Ticket ticket = new Ticket(ticketId, description, Status.RESOLVED, LocalDateTime.now());
        ticket.setResolutionSummary(resolutionSummary);
        Ticket savedTicket = new Ticket(ticketId, description, Status.CLOSED, LocalDateTime.now());
        savedTicket.setResolutionSummary(resolutionSummary);
        savedTicket.setClosedDate(LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        TicketDto updatedTicket = ticketService.closeTicket(ticketId);

        assertEquals(Status.CLOSED, updatedTicket.status());
        assertNotNull(updatedTicket.closedDate());
    }

    @Test
    void givenNonexistentTicket_whenClosing_thenThrowException() {
        Long nonExistentTicketId = 999L;

        when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.closeTicket(nonExistentTicketId)
        );
    }

    @Test
    void givenResolvedTicketWithoutSummary_whenClosing_thenThrowException() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, "Description", Status.RESOLVED, LocalDateTime.now());

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        assertThrows(MissingResolutionSummaryException.class,
                () -> ticketService.closeTicket(ticketId)
        );
    }

    @Test
    void givenTicketNotInResolvedState_whenClosing_thenThrowException() {
        Long ticketId = 1L;
        String description = "Description";
        Ticket ticket = new Ticket(ticketId, description, Status.NEW, LocalDateTime.now());
        ticket.setResolutionSummary("Summary");

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        assertThrows(InvalidTicketStateException.class,
                () -> ticketService.closeTicket(ticketId)
        );
    }
}
