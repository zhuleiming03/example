package com.example.jpa.serviceImpl;

import com.example.jpa.po.ScorePO;
import com.example.jpa.repository.ScoreRepository;
import com.example.jpa.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository repository;

    public ScorePO insert(ScorePO po) {
        return repository.save(po);
    }

    public Integer delete(Integer scoreID) {
        if (scoreID == null) {
            return null;
        } else {
            return repository.deleteByScoreID(scoreID);
        }
    }

    public Integer updateScoreByScoreID(Float score, Integer scoreID) {
        if (scoreID == null || score == null) {
            return null;
        } else {
            return repository.updateScoreByScoreID(scoreID, score);
        }
    }

    public ScorePO selectByScoreID(Integer scoreID) {
        if (scoreID == null) {
            return null;
        } else {
            return repository.findByScoreID(scoreID);
        }
    }

    public List<ScorePO> selectAll() {
        return repository.findAll();
    }
}
