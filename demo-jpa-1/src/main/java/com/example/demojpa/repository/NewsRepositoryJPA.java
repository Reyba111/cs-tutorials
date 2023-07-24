package com.example.demojpa.repository;

import com.example.demojpa.model.News;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsRepositoryJPA {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void save(News news) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            // save to database
            entityManager.persist(news);
            transaction.commit();
        }
    }

    public News findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(News.class, id);
        }
    }

}
