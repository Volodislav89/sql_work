package com.spring_sql.sql_work.service;

import com.spring_sql.sql_work.model.Cow;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CowService {
//    @PersistenceContext
//    EntityManager entityManager;
    SessionFactory sessionFactory;

    public CowService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Cow> getAll() {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM Cow", Cow.class).getResultList();
    }

    public List<Cow> getCriteriaCow() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        //CriteriaQuery параметризируется типом который будет возвращать
        CriteriaQuery<Cow> criteriaQuery = criteriaBuilder.createQuery(Cow.class);
        //мсто откуда будут браться данные
        Root<Cow> cowRoot = criteriaQuery.from(Cow.class);
        criteriaQuery.select(cowRoot);
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    public List<String> getCowsCriteriaSum() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Cow> cowRoot = criteriaQuery.from(Cow.class);
        criteriaQuery.select(cowRoot.get("name"));
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    public Integer ageSum() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<Cow> cowRoot = criteriaQuery.from(Cow.class);
        criteriaQuery.select(criteriaBuilder.sum(cowRoot.get("age")));
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getSingleResult();
    }
}
