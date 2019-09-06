package com.example.jpa.repository;

import com.example.jpa.po.ScorePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ScoreRepository extends JpaRepository<ScorePO,Integer> {

    @Query(value = "SELECT t.*,s.StudentName,c.CourseName,c.CourserCode FROM dbo.Score t INNER JOIN dbo.Student s ON s.StudentID = t.StudentID INNER JOIN dbo.Course c ON c.CourseID = t.CourseID ", nativeQuery = true)
    List<ScorePO> findAll();

    @Query(value = "SELECT t.*,s.StudentName,c.CourseName,c.CourserCode FROM dbo.Score t INNER JOIN dbo.Student s ON s.StudentID = t.StudentID INNER JOIN dbo.Course c ON c.CourseID = t.CourseID WHERE t.ScoreID= ?1 ", nativeQuery = true)
    ScorePO findByScoreID(Integer scoreID);

    @Query(value = "DELETE dbo.Score WHERE ScoreID= ?1", nativeQuery = true)
    @Modifying
    @Transactional
    Integer deleteByScoreID(Integer scoreID);

    @Query(value = "UPDATE dbo.Score SET Score= ?2 WHERE ScoreID= ?1", nativeQuery = true)
    @Modifying
    @Transactional
    Integer updateScoreByScoreID(Integer ScoreID, Float score);
}
