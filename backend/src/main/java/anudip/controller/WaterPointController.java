package anudip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import anudip.entity.WaterPoint;
import anudip.service.WaterPointService;

@RestController
@RequestMapping("/api/waterpoints")
public class WaterPointController {

    @Autowired
    private WaterPointService waterPointService;

    // Add Water Point
    @PostMapping
    public WaterPoint addWaterPoint(@RequestBody WaterPoint waterPoint) {
        return waterPointService.addWaterPoint(waterPoint);
    }

    // Get All Water Points
    @GetMapping
    public List<WaterPoint> getAllWaterPoints() {
        return waterPointService.getAllWaterPoints();
    }

    // Get Water Point By Id
    @GetMapping("/{id}")
    public WaterPoint getWaterPointById(@PathVariable Long id) {
        return waterPointService.getWaterPointById(id);
    }

    // Get Water Points By Route Stop
    @GetMapping("/stop/{stopId}")
    public List<WaterPoint> getWaterPointsByRouteStop(@PathVariable Long stopId) {
        return waterPointService.getWaterPointsByRouteStop(stopId);
    }

    // Update Water Point
    @PutMapping("/{id}")
    public WaterPoint updateWaterPoint(@PathVariable Long id,
                                       @RequestBody WaterPoint waterPoint) {
        return waterPointService.updateWaterPoint(id, waterPoint);
    }

    // Delete Water Point
    @DeleteMapping("/{id}")
    public String deleteWaterPoint(@PathVariable Long id) {
        return waterPointService.deleteWaterPoint(id);
    }
}
