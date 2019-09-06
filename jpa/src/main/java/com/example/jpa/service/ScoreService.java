package com.example.jpa.service;

import com.example.jpa.po.ScorePO;

import java.util.List;

public interface ScoreService {

    ScorePO insert(ScorePO po);

    Integer delete(Integer scoreID);

    Integer updateScoreByScoreID (Float birthday, Integer scoreID );

    ScorePO selectByScoreID (Integer scoreID );

    List<ScorePO> selectAll();

}
