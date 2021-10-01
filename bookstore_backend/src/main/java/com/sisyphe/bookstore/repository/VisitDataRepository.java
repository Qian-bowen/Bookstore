package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.VisitData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitDataRepository extends JpaRepository<VisitData,Integer> {
    @Query(value = "select count(v) from VisitData v")
    Integer getRecordNumber();

    @Query(value = "select v from VisitData v order by v.recordTime DESC")
    List<VisitData> getTopRecordByRecordTime(Pageable pageable);
}
