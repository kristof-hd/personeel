package be.vdab.personeel.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.web.RijksregisternummerForm;

public class RijksregisternummerFormValidator implements ConstraintValidator<Rijksregisternummer, RijksregisternummerForm> {

	@Override
	public void initialize(Rijksregisternummer arg0) {
	}
	
	@Override
	public boolean isValid(RijksregisternummerForm form, ConstraintValidatorContext context) {
	
		long rijksregisternr = form.getRijksregisternr(); 
		LocalDate geboorte=LocalDate.parse(form.getGeboorteAlsString());
		
		Werknemer werknemer = new Werknemer(); 
		werknemer.setRijksregisternr(rijksregisternr);
		werknemer.setGeboorte(geboorte);
		return werknemer.isGeldigRijksregisternr(); 
		
	}
}