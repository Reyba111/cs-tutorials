package com.example.demojpa;

import com.example.demojpa.model.News;
import com.example.demojpa.repository.NewsRepositoryJDBC;
import com.example.demojpa.repository.NewsRepositoryJPA;
import com.example.demojpa.repository.NewsRepositorySpring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoJpaApplicationTests {

    @Autowired
    private NewsRepositoryJDBC newsRepositoryJDBC;

    @Autowired
    private NewsRepositoryJPA newsRepositoryJPA;

    @Autowired
    private NewsRepositorySpring newsRepositorySpring;

    @Test
    void jdbc() {
        final News news = new News("foo", "de");
        newsRepositoryJDBC.save(news);

        final News byId = newsRepositoryJDBC.findById(news.getId());
        Assertions.assertNotNull(byId);
    }

    @Test
    void jpa() {
        final News news = new News("bar", "en");
        newsRepositoryJPA.save(news);

        final News byId = newsRepositoryJPA.findById(news.getId());
        Assertions.assertNotNull(byId);
    }

    @Test
    void spring() {
        final News newsDe = new News("abc", "de");
        newsRepositorySpring.save(newsDe);

        final News newsEn = new News("def", "en");
        newsRepositorySpring.save(newsEn);

        final News byId = newsRepositorySpring.findById(newsEn.getId()).orElseThrow();
        Assertions.assertNotNull(byId);

        final List<News> allNews = newsRepositorySpring.findAll();
        Assertions.assertEquals(2, allNews.size());

        final List<News> germanNews = newsRepositorySpring.findAllByLanguage("de");
        Assertions.assertEquals(1, germanNews.size());
        Assertions.assertEquals("de", germanNews.get(0).getLanguage());

        final List<News> allWithTitleStartingWithA = newsRepositorySpring.findAllByTitleStartingWith("a");
        Assertions.assertEquals(1, allWithTitleStartingWithA.size());
        Assertions.assertEquals(newsDe.getId(), allWithTitleStartingWithA.get(0).getId());
    }

}
