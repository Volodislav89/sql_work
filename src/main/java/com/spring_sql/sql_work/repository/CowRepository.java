package com.spring_sql.sql_work.repository;

import com.spring_sql.sql_work.model.Cow;
import com.spring_sql.sql_work.model.CowHorseJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CowRepository extends JpaRepository<Cow, Long> {
    @Query(value = "SELECT * FROM cow WHERE color=?1", nativeQuery = true)
    List<Cow> findCowsByColorSQL(String color);

    @Query("SELECT c FROM Cow c WHERE c.color=:color")
    List<Cow> findCowByColorJPQL(@Param("color") String color);

    List<Cow> findCowsByColor(String color);

    List<Cow> aaa(int age);

    @Query(value = "select new com.spring_sql.sql_work.model.CowHorseJoin(c.name, h.name) from Cow c left join Horse h on c.age = h.age")
    List<CowHorseJoin> findHorseCowJoin();
}
