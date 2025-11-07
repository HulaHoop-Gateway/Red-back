package com.hulahoop.redback.statistics.model.dao;

import java.util.List;
import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
    List<StatisticsDTO> selectDailyStatistics();
}
