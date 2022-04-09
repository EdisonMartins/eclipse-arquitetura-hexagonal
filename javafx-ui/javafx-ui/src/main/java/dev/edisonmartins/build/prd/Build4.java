package dev.edisonmartins.build.prd;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration: Responsavel por configurar os serviços do spring
 * @ComponentScan: Configura as diretivas de varredura.
 *
 * conta.sistema: Pacote do Hexágono
 * conta.servicos.repositorio: Pacote dos Adaptadores Secundários, ou seja, são as implementações dos repositórios.
 *
 * Build 3: Adaptador Interface Gráfica → Sistema ← Adaptadores Real em Homologação
 *          O Objetivo é homologar o sistema.
 *
 * **/

@Configuration
@EnableTransactionManagement
@ComponentScan({
        "dev.edisonmartins.build.prd",
        "dev.edisonmartins.scene",
        "dev.edisonmartins.ports.in.imp",
        "dev.edisonmartins.domain.service",
        "dev.edisonmartins.repositories",
    })
public class Build4 {
	public Build4() {
		System.out.println("Build4()");
	}

    @Bean
    public DataSource dataSource() {
        var db = new SimpleDriverDataSource();
        db.setDriverClass(org.hsqldb.jdbcDriver.class);
        db.setUrl("jdbc:hsqldb:file:C:/CodigoCurso/Arquitetura-hexagonal-db/");
        db.setUsername("SA");
        db.setPassword("1234");
        return db;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
