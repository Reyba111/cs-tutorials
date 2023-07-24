package com.example.demojpa.repository;

import com.example.demojpa.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepositorySpring extends JpaRepository<News, Long> {

    List<News> findAllByLanguage(String language);

    //@Query("select n from News n where n.title ilike 'a%'")
    List<News> findAllByTitleStartingWith(String prefix);

}
