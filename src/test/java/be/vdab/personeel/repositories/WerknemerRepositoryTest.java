package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertWerknemer.sql") 
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String WERKNEMERS = "werknemers";
	@Autowired
	private WerknemerRepository repository;

	private long idVanTestWerknemer() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where familienaam='test'", Long.class);
	}
	
	@Test
	public void update() {
		long id = idVanTestWerknemer();
		assertEquals(0,BigDecimal.valueOf(2000)
				.compareTo(super.jdbcTemplate.queryForObject("select salaris from werknemers where id=?",  BigDecimal.class, id)));

		Jobtitel jobtitel = new Jobtitel("test", 0);
		Werknemer werknemer = new Werknemer(id, "test", "test", "test@test.com", jobtitel, BigDecimal.valueOf(2100), "test", LocalDate.of(1999, 1,  1), 1, 0); 
		repository.save(werknemer);

		assertEquals(0,BigDecimal.valueOf(2100)
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
	
	@Test
	public void findOndergeschikten() {
		long id = idVanTestWerknemer(); 
		List<Werknemer> ondergeschikten = repository.findOndergeschikten(id);
		for (Werknemer ondergeschikte: ondergeschikten) {
			assertEquals((long) ondergeschikte.getChefid(), id); 
		}
		assertEquals(super.countRowsInTableWhere(WERKNEMERS, "chefid="+id), ondergeschikten.size()); 
		
	}
	
	@Test
	public void findByJobtitelNaam() {
		List<Werknemer> werknemers = repository.findByJobtitelNaam("test"); 
		for (Werknemer werknemer: werknemers) {
			assertEquals("test", werknemer.getJobtitel().getNaam());
		}
		long aantalWerknemers = super.countRowsInTableWhere(WERKNEMERS, "jobtitelid=(select id from jobtitels where naam='test')"); 
		assertEquals(aantalWerknemers, werknemers.size()); 
	}
	
}