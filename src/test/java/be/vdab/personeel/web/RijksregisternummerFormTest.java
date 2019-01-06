package be.vdab.personeel.web;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RijksregisternummerFormTest {

	private Validator validator;

	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void rijksregisternrOk() {
		Set<ConstraintViolation<RijksregisternummerForm>> violations = validator
				.validateValue(RijksregisternummerForm.class, "rijksregisternr", BigDecimal.ONE, 
						RijksregisternummerForm.class, "geboorte", LocalDate.of(1978,  11,  29));
		assertTrue(violations.isEmpty());
	}

	@Test
	public void vanMoetIngeVuldZijn() {
		Set<ConstraintViolation<VanTotPrijsForm>> violations = validator.validateValue(VanTotPrijsForm.class, "van",
				null);
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".NotNull."));
	}

	@Test
	public void vanMoetMinstensNulZijn() {
		Set<ConstraintViolation<VanTotPrijsForm>> violations = validator.validateValue(VanTotPrijsForm.class, "van",
				BigDecimal.valueOf(-1));
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".Min."));
	}

}