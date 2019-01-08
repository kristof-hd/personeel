package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeel.entities.Jobtitel;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitel.sql") 
public class JobtitelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String JOBTITELS = "jobtitels";

	@Autowired
	private JobtitelRepository repository;

	@Test
	public void findAll() {
		List<Jobtitel> jobtitels = repository.findAll();
		assertEquals(super.countRowsInTable(JOBTITELS), jobtitels.size()); 
	}
	
}