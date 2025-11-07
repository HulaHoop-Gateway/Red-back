package com.hulahoop.redback.statistics.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;
import com.hulahoop.redback.statistics.model.service.StatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public List<StatisticsDTO> getDailyStatistics() {
        return statisticsService.getDailyStatistics();
    }
}
