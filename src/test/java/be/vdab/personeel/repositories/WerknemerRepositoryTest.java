package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeel.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertWerknemer.sql") 
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private WerknemerRepository repository;
	@Autowired
	private EntityManager manager;

	private long idVanTestWerknemer() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where familienaam='test'", Long.class);
	}
	
	@Test
	public void update() {
		long id = idVanTestWerknemer();
		Werknemer werknemer=manager.find(Werknemer.class, id);
		werknemer.opslag(BigDecimal.TEN);
		repository.save(werknemer);
		manager.flush();
		assertEquals(0,BigDecimal.valueOf(2010)
			.compareTo(super.jdbcTemplate.queryForObject("select salaris from werknemers where id=?",  BigDecimal.class, id)));
	}

	@Test
	public void read() {
		Werknemer werknemer = repository.findById(idVanTestWerknemer()).get(); 
		assertEquals("test", werknemer.getFamilienaam()); 
	}

	@Test
	public void readOnbestaandeWerknemer() {
		Optional<Werknemer> werknemer = repository.findById(-1L); 
		assertFalse(werknemer.isPresent()); 
	}
	
	@Test
	public void findMetHoogsteHierarchie() {
		long id = super.jdbcTemplate.queryForObject("select id from werknemers where chefid is null", Long.class);
		Werknemer werknemerMetHoogsteHierarchie = repository.findMetHoogsteHierarchie();
		assertEquals(id, werknemerMetHoogsteHierarchie.getId());		
	}

}