package anudip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import anudip.entity.Route;
import anudip.service.RouteService;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Add Route with Stops
    @PostMapping
    public Route addRoute(@RequestBody Route route) {
        return routeService.addRoute(route);
    }

    // Get All Routes
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    // Get Route By ID
    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }

    // Delete Route
    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable Long id) {
        return routeService.deleteRoute(id);
    }
}