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

public class RijksregisternummerFormTest {

private Validator validator; 
@Before
public void before() {
ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); 
validator = factory.getValidator(); 
}
@Test
public void vanOk() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class, "van", BigDecimal.ONE);
assertTrue(violations.isEmpty());
}
@Test
public void vanMoetIngeVuldZijn() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class, "van", null);
assertEquals(1, violations.size());
assertTrue(violations.iterator().next().
getMessageTemplate().contains(".NotNull."));
}
@Test
public void vanMoetMinstensNulZijn() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class,"van",BigDecimal.valueOf(-1));
assertEquals(1, violations.size());
assertTrue(violations.iterator().next()
.getMessageTemplate().contains(".Min."));
}
@Test
public void totOk() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class, "van", BigDecimal.ONE);
assertTrue(violations.isEmpty());
}
@Test
public void totMoetIngeVuldZijn() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class, "tot", null);
assertEquals(1, violations.size());
assertTrue(violations.iterator().next()
.getMessageTemplate().contains(".NotNull."));
}
@Test
public void totnMoetMinstensNulZijn() {
Set<ConstraintViolation<VanTotPrijsForm>> violations =
validator.validateValue(VanTotPrijsForm.class,"tot",BigDecimal.valueOf(-1));
assertEquals(1, violations.size());
assertTrue(violations.iterator().next()
.getMessageTemplate().contains(".Min."));
}
}