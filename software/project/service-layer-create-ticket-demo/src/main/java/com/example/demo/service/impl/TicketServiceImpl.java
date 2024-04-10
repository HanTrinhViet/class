package com.example.demo.service.impl;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket newTicket = new Ticket();
        newTicket.setDescription(ticketDto.description());

        Ticket savedTicket = ticketRepository.save(newTicket);

        return new TicketDto(
                savedTicket.getId(),
                savedTicket.getDescription()
        );
    }

}
