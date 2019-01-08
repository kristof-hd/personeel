package be.vdab.personeel.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
	
	@Test
	public void isGeldigRijksregisternr() {
 		
	//correcte rijksregisternummers: 

		werknemer1.setRijksregisternr(10011705866L);
		werknemer1.setGeboorte(LocalDate.of(2010, 1, 17));
		assertTrue(werknemer1.isGeldigRijksregisternr()); 

		werknemer1.setRijksregisternr(10011708143L);
		werknemer1.setGeboorte(LocalDate.of(2010, 1, 17));
		assertTrue(werknemer1.isGeldigRijksregisternr()); 		
		
		werknemer1.setRijksregisternr(7030612091L);
		werknemer1.setGeboorte(LocalDate.of(2007, 3, 6));
		assertTrue(werknemer1.isGeldigRijksregisternr()); 		
		
		werknemer1.setRijksregisternr(78112923726L);
		werknemer1.setGeboorte(LocalDate.of(1978, 11, 29));
		assertTrue(werknemer1.isGeldigRijksregisternr());
		
	//verkeerd controlegetal  

		werknemer1.setRijksregisternr(10011705865L);
		werknemer1.setGeboorte(LocalDate.of(2010, 1, 17));
		assertFalse(werknemer1.isGeldigRijksregisternr()); 
		
		werknemer1.setRijksregisternr(78112923725L);
		werknemer1.setGeboorte(LocalDate.of(1978, 11, 29));
		assertFalse(werknemer1.isGeldigRijksregisternr());
		
	//geen overeenkomst met geboortedatum: 

		werknemer1.setRijksregisternr(10011805866L);
		werknemer1.setGeboorte(LocalDate.of(2010, 1, 17));
		assertFalse(werknemer1.isGeldigRijksregisternr()); 
			
		werknemer1.setRijksregisternr(78113023726L);
		werknemer1.setGeboorte(LocalDate.of(1978, 11, 29));
		assertFalse(werknemer1.isGeldigRijksregisternr());
		
	}
}