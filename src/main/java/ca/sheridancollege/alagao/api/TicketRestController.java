package ca.sheridancollege.alagao.api;

import ca.sheridancollege.alagao.beans.Ticket;
import ca.sheridancollege.alagao.database.TicketDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    @Autowired
    private TicketDatabase ticketDatabase;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketDatabase.findAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketDatabase.findTicketById(id);
    }

    @PostMapping(consumes = "application/json")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        ticketDatabase.insertTicket(ticket);
        return ticket; // Ideally, return the ticket with an ID if it's generated by the database
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        ticket.setTicketID(id);
        ticketDatabase.updateTicket(ticket);
        return ticket;
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketDatabase.deleteTicket(id);
        return "Ticket with ID: " + id + " deleted successfully";
    }

    // Additional methods as needed
}
