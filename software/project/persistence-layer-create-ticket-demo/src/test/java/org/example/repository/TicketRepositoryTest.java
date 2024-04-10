package org.example.repository;

import org.example.model.Status;
import org.example.model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepo;


    @Test
    @Rollback(value = false)
    public void givenTicketDetails_whenSaveByRepository_thenReturnTicket() {
        Ticket ticket = Ticket.builder()
                .id(null)
                .description("ticket number 1")
                .status(Status.NEW)
                .build();

        Ticket createdTicket = ticketRepo.save(ticket);
        assertNotNull(createdTicket);
        assertEquals(createdTicket.getId(), 1L);
        assertEquals(createdTicket.getDescription(), "ticket number 1");
    }


}