package net.braniumacademy.service;

import net.braniumacademy.dto.TicketDTO;


public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);

    TicketDTO assignAgentToTicket(Long ticketId, Long agentId);
}
