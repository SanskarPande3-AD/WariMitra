package anudip.controller;

import anudip.entity.Event;
import anudip.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create Event
    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    // Get All Events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get Event By ID
    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // Update Event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    // Delete Event
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
