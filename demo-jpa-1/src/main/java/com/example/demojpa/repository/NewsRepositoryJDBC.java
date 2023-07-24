package com.example.demojpa.repository;

import com.example.demojpa.model.News;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

@Component
public class NewsRepositoryJDBC {

    // Just for demo purposes. In the real world the database should choose the next free sequence value.
    public static final Random RANDOM = new Random();
    private final DataSource dataSource;

    public NewsRepositoryJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(News news) {

        try (Connection connection = dataSource.getConnection()) {
            final String sql = "INSERT INTO news (id, title, language) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                final long id = RANDOM.nextLong();
                news.setId(id);
                statement.setLong(1, id);
                statement.setString(2, news.getTitle());
                statement.setString(3, news.getLanguage());

                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public News findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT * FROM news WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);

                final ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    final News news = new News();
                    news.setId(resultSet.getLong("id"));
                    news.setTitle(resultSet.getString("title"));
                    news.setLanguage(resultSet.getString("language"));

                    // always return first result and do not check for more
                    return news;

                } else {
                    // no data
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
