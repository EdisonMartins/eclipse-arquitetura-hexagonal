package dev.edisonmartins.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import dev.edisonmartins.domain.service.Transferencia;
import dev.edisonmartins.ports.in.PortaTransferencia;
import dev.edisonmartins.ports.in.imp.PortaTransferenciaImp;
import dev.edisonmartins.ports.out.ContaRepositorio;
import dev.edisonmartins.repositories.ContaRepositorioImp;

@ApplicationScoped
public class PortaTransferenciaProducer {
	
	@Inject
	private ContaRepositorio repositorio;
	
	@Inject
	private Transferencia transferencia;
	
	@Produces
	public PortaTransferencia criaPortaTransferencia() {
		return new PortaTransferenciaImp(repositorio, transferencia);
		
	}
	
	@Produces
	public ContaRepositorio criaContaRepositorio(JdbcTemplate jdbcTemplate) {
		return new ContaRepositorioImp(jdbcTemplate);
	}
	
	@Produces
    public DataSource dataSource() {
    	SimpleDriverDataSource db = new SimpleDriverDataSource();
        db.setDriverClass(org.hsqldb.jdbcDriver.class);
        db.setUrl("jdbc:hsqldb:file:E:/PROGRAMACAO/BancoEmMemoria/DB/ArquiteturaHexagonal");
        db.setUsername("SA");
        db.setPassword("1234");
        return db;
    }

	@Produces
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

	@Produces
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
	
	@Produces
	public Transferencia criaTransferencia() {
		return new Transferencia();
	}

	
	
}
