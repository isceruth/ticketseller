package team.componenets.bookingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.componenets.bookingservice.model.dto.BookTicketDto;
import team.componenets.bookingservice.model.service.BookingService;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    ResponseEntity<?> bookTicket(@RequestBody BookTicketDto dto) {
        return bookingService.bookTicket(dto) ? ResponseEntity.ok("Ticket booked!") : ResponseEntity.ok("Ticket not booked!");
    }
}
