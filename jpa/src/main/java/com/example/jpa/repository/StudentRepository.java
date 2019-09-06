package com.example.jpa.repository;

import com.example.jpa.po.StudentPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends JpaRepository<StudentPO,Integer> {

    @Query(value = "SELECT StudentID,StudentName,Birthday FROM dbo.Student WHERE StudentID= ?1 ", nativeQuery = true)
    StudentPO findByStudentID(Integer studentID);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.Student SET Birthday= ?2 WHERE StudentID= ?1 ", nativeQuery = true)
    Integer changeBirthdayByStudentID(Integer studentID, LocalDateTime birthday);

    @Transactional
    @Modifying
    @Query(value = "DELETE dbo.Student WHERE StudentID= ?1 ", nativeQuery = true)
    Integer removeByStudentID(Integer studentID);

    @Query(value = "SELECT StudentID,StudentName,Birthday FROM dbo.Student ", nativeQuery = true)
    List<StudentPO> findAll();
}
