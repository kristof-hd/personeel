package be.vdab.personeel.web;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OpslagFormTest {

	private Validator validator;

	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void bedragOk() {
		Set<ConstraintViolation<OpslagForm>> violations = validator.validateValue(OpslagForm.class, "bedrag",
				BigDecimal.ONE);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void bedragMoetIngevuldZijn() {
		Set<ConstraintViolation<OpslagForm>> violations = validator.validateValue(OpslagForm.class, "bedrag", null);
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".NotNull."));
	}

	@Test
	public void bedragMoetMinstens1Zijn() {
		Set<ConstraintViolation<OpslagForm>> violations = validator.validateValue(OpslagForm.class, "bedrag",
				BigDecimal.valueOf(0.5));
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".Min."));
	}

	@Test
	public void bedragMagNietNegatiefZijn() {
		Set<ConstraintViolation<OpslagForm>> violations = validator.validateValue(OpslagForm.class, "bedrag",
				BigDecimal.valueOf(-1));
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".Min."));
	}	
	
}