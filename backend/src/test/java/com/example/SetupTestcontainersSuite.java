package com.example;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.javafaker.Faker;

@Testcontainers
public abstract class SetupTestcontainersSuite {

  @BeforeAll
  static void beforeAll() {
    // Programmatic Configuration of Database
    Flyway flyway = Flyway
        .configure()
        .dataSource(
            postgreSQLContainer.getJdbcUrl(),
            postgreSQLContainer.getUsername(),
            postgreSQLContainer.getPassword())
        .load();
    flyway.migrate();
  }

  @Container
  protected static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
      .withDatabaseName("postgres-dao-unit-test")
      .withUsername("postgres")
      .withPassword("password");

  @DynamicPropertySource
  private static void registerDataSourceProperties(DynamicPropertyRegistry dataSourceProperties) {
    // Overrides Properties of Application (application.yaml) to connect to the DB
    // Instance which is run by Docker
    dataSourceProperties.add(
        "spring.datasource.url",
        postgreSQLContainer::getJdbcUrl);
    dataSourceProperties.add(
        "spring.datasource.username",
        postgreSQLContainer::getUsername);
    dataSourceProperties.add(
        "spring.datasource.password",
        postgreSQLContainer::getPassword);
  }

  private static DataSource getDataSource() {
    return DataSourceBuilder.create()
        .driverClassName(postgreSQLContainer.getDriverClassName())
        .url(postgreSQLContainer.getJdbcUrl())
        .username(postgreSQLContainer.getUsername())
        .password(postgreSQLContainer.getPassword())
        .build();
  }

  protected static JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }

  protected static final Faker FAKER = new Faker();
}
