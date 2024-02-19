package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.dto.TicketFilterDto;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        TicketDto createdTicket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/agent/{agentId}")
    public ResponseEntity<TicketDto> assignAgent(@PathVariable Long id, @PathVariable Long agentId) {
        TicketDto updatedTicket = ticketService.assignAgentToTicket(id, agentId);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<TicketDto> resolveTicket(@PathVariable Long id) {
        TicketDto resolvedTicket = ticketService.resolveTicket(id);
        return new ResponseEntity<>(resolvedTicket, HttpStatus.OK);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<TicketDto> closeTicket(@PathVariable Long id) {
        TicketDto closedTicket = ticketService.closeTicket(id);
        return new ResponseEntity<>(closedTicket, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto updatedTicketDetails) {
        TicketDto updatedTicket = ticketService.updateTicket(id, updatedTicketDetails);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getTickets(TicketFilterDto filter) {
        List<TicketDto> tickets = ticketService.getTickets(filter);
        return ResponseEntity.ok(tickets);
    }
}
