package net.braniumacademy.dto;

import net.braniumacademy.model.Status;

import java.time.LocalDateTime;

public record TicketDTO(Long id,
                        String description,
                        Status status,
                        LocalDateTime createdDate,
                        LocalDateTime closedDate,
                        String assignedAgent,
                        String resolutionSummary) {
}
