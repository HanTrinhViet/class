package com.example.demo.model;

import java.util.Objects;

public class Ticket {
    private Long id;

    private String description;


    public Ticket() {
    }

    public Ticket(Long id, String description) {
        this.id = id;
        this.description = description;

    }

    public Long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id)
                && Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
