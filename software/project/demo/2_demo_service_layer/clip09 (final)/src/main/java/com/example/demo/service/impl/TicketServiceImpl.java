package com.example.demo.service.impl;

import com.example.demo.dto.TicketDto;
import com.example.demo.dto.TicketFilterDto;
import com.example.demo.exception.*;
import com.example.demo.model.Agent;
import com.example.demo.model.Status;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import com.example.demo.util.ErrorMessages;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    private final AgentRepository agentRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, AgentRepository agentRepository) {
        this.ticketRepository = ticketRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        if (ticketDto.description() == null || ticketDto.description().isEmpty()) {
            throw new MissingDescriptionException(ErrorMessages.DESCRIPTION_REQUIRED);
        }

        Ticket newTicket = new Ticket();
        newTicket.setDescription(ticketDto.description());
        newTicket.setStatus(Status.NEW);
        newTicket.setCreatedDate(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(newTicket);

        return convertToDto(savedTicket);
    }

    @Override
    public TicketDto assignAgentToTicket(Long ticketId, Long agentId) {
        Ticket existingTicket = getTicket(ticketId);

        if (existingTicket.getStatus() != Status.NEW) {
            throw new InvalidTicketStateException(ErrorMessages.ONLY_NEW_TICKETS_CAN_BE_ASSIGNED_TO_AN_AGENT);
        }

        Agent assignedAgent = agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException(ErrorMessages.AGENT_NOT_FOUND));

        existingTicket.setStatus(Status.IN_PROGRESS);
        existingTicket.setAssignedAgent(assignedAgent);

        Ticket savedTicket = ticketRepository.save(existingTicket);

        return convertToDto(savedTicket);
    }

    @Override
    public TicketDto resolveTicket(Long ticketId) {
        Ticket existingTicket = getTicket(ticketId);

        if (existingTicket.getStatus() != Status.IN_PROGRESS) {
            throw new InvalidTicketStateException(ErrorMessages.ONLY_TICKETS_IN_PROGRESS_CAN_BE_RESOLVED);
        }

        existingTicket.setStatus(Status.RESOLVED);
        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return convertToDto(updatedTicket);
    }

    @Override
    public TicketDto closeTicket(Long ticketId) {
        Ticket existingTicket = getTicket(ticketId);

        validateTicketBeforeClosing(existingTicket);

        existingTicket.setStatus(Status.CLOSED);
        existingTicket.setClosedDate(LocalDateTime.now());
        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return convertToDto(updatedTicket);
    }

    @Override
    public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) {
        Ticket existingTicket = getTicket(ticketId);

        if (existingTicket.getStatus() == Status.CLOSED) {
            throw new InvalidTicketStateException(ErrorMessages.CLOSED_TICKETS_CANNOT_BE_UPDATED);
        }

        existingTicket.setDescription(ticketDto.description());
        existingTicket.setResolutionSummary(ticketDto.resolutionSummary());
        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return convertToDto(updatedTicket);
    }

    @Override
    public TicketDto getTicketById(Long ticketId) {
        Ticket existingTicket = getTicket(ticketId);

        return convertToDto(existingTicket);
    }

    @Override
    public List<TicketDto> getTickets(TicketFilterDto ticketFilterDto) {
        if (ticketFilterDto.startDate() != null && ticketFilterDto.endDate() != null &&
                ticketFilterDto.endDate().isBefore(ticketFilterDto.startDate())) {
            throw new InvalidDateRangeException(ErrorMessages.INVALID_DATE_RANGE);
        }

        List<Ticket> filteredTickets = ticketRepository.findWithFilters(
                ticketFilterDto.status(),
                ticketFilterDto.startDate(),
                ticketFilterDto.endDate(),
                ticketFilterDto.assignedAgent()
        );

        return filteredTickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TicketDto convertToDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedDate(),
                ticket.getClosedDate(),
                ticket.getAssignedAgent() != null ? ticket.getAssignedAgent().getName() : null,
                ticket.getResolutionSummary()
        );
    }

    private Ticket getTicket(Long ticketId) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ErrorMessages.TICKET_NOT_FOUND));
        return existingTicket;
    }

    private void validateTicketBeforeClosing(Ticket existingTicket) {
        if (existingTicket.getResolutionSummary() == null
                || existingTicket.getResolutionSummary().isEmpty()) {
            throw new MissingResolutionSummaryException(ErrorMessages.RESOLUTION_SUMMARY_REQUIRED);
        }

        if (existingTicket.getStatus() != Status.RESOLVED) {
            throw new InvalidTicketStateException(ErrorMessages.ONLY_RESOLVED_TICKETS_CAN_BE_CLOSED);
        }
    }
}
