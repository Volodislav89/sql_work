package com.spring_sql.sql_work.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Cow.aaa",
                        query = "SELECT * FROM cow WHERE cow.age > ?1",
                        resultClass = Cow.class
                )

        }
)
public class Cow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private int age;
}
