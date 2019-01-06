package be.vdab.personeel.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test; 

public class WerknemerTest {

	private static final BigDecimal ORIGINEEL_SALARIS = BigDecimal.valueOf(2000); 
	private Jobtitel jobtitel1; 
	private Werknemer werknemer1; 
	
	@Before
	public void before() {
		jobtitel1 = new Jobtitel("test", 0); 
		werknemer1 = new Werknemer("test", "test", "test@test.com", jobtitel1, ORIGINEEL_SALARIS, "test", LocalDate.of(1999, 1,  1), 1, 0);
 
	}
	
	@Test
	public void opslag() {
		werknemer1.opslag(BigDecimal.valueOf(100));
		assertEquals(0, BigDecimal.valueOf(2100).compareTo(werknemer1.getSalaris())); 
	}

	@Test(expected=NullPointerException.class)
	public void opslagMetNullKanNiet() {
		werknemer1.opslag(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void opslagMetEenHalfKanNiet() {
		werknemer1.opslag(BigDecimal.valueOf(0.5));
		assertEquals(0, ORIGINEEL_SALARIS.compareTo(werknemer1.getSalaris())); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void opslagMetEenNegatiefBedragKanNiet() {
		werknemer1.opslag(BigDecimal.valueOf(-1));
		assertEquals(0, ORIGINEEL_SALARIS.compareTo(werknemer1.getSalaris())); 
	}
	
	
}
