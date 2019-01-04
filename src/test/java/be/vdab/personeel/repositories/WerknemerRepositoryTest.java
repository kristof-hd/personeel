package be.vdab.personeel.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String FILIALEN = "filialen";
	@Autowired
	private WerknemerRepository repository;

	@Test
	public void update() {
		Adres adres = new Adres("straat", "huisNr", 1000, "gemeente");
		Filiaal filiaal = new Filiaal();
		filiaal.setNaam("test");
		filiaal.setAdres(adres);
		filiaal.setWaardeGebouw(BigDecimal.ZERO);
		filiaal.setInGebruikName(LocalDate.now());
		int aantalFilialen = super.countRowsInTable(FILIALEN);
		repository.save(werknemer);
		assertEquals(aantalFilialen + 1, super.countRowsInTable(FILIALEN));
		assertNotEquals(0, filiaal.getId());
		assertEquals(1, super.countRowsInTableWhere(FILIALEN, "id=" + filiaal.getId()));
	}
}