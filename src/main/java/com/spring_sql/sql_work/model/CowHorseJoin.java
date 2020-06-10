package com.spring_sql.sql_work.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CowHorseJoin implements Serializable {
    private String cowName;
    private String horseName;
}
