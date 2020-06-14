package com.spring_sql.sql_work.service;

import com.spring_sql.sql_work.model.Horse;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Service
@Transactional
public class HorseService {
    SessionFactory sessionFactory;

    public HorseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Horse> getAllHorses() {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM horse", Horse.class)
                .getResultList();
    }

    public Horse getHorseById(Long id) {
        return (Horse) sessionFactory.getCurrentSession()
                .createQuery("FROM Horse h WHERE h.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveHorse(Horse horse) {
        sessionFactory.getCurrentSession().save(horse);
    }
}
