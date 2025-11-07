package com.hulahoop.redback.statistics.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hulahoop.redback.statistics.model.dao.StatisticsMapper;
import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public List<StatisticsDTO> getDailyStatistics() {
        return statisticsMapper.selectDailyStatistics();
    }
}
