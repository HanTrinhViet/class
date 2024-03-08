package net.braniumacademy.controller;


import net.braniumacademy.dto.TicketDTO;
import net.braniumacademy.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicker(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/agent/{agentId}")
    public ResponseEntity<TicketDTO> assignAgent(@PathVariable("id") Long id,
                                                 @PathVariable("agentId") Long agentId) {
        TicketDTO updatedTicket = ticketService.assignAgentToTicket(id, agentId);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }
}
