package com.rocketseat.certification_nlw.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseSeeding {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSeeding(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pg_nlw");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");

        DatabaseSeeding databaseSeeding = new DatabaseSeeding(dataSource);
        databaseSeeding.run(args);

    }

    public void run(String... args) {
        executeSqlFile("src/main/resources/create.sql");
    }

    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao executar o arquivo! " + e.getMessage());
        }

    }
}
