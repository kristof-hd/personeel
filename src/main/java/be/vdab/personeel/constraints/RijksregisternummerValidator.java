package be.vdab.personeel.constraints;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.web.RijksregisternummerForm;

public class RijksregisternummerValidator implements ConstraintValidator<Rijksregisternummer, RijksregisternummerForm> {

	@Override
	public void initialize(Rijksregisternummer arg0) {
	}
	
	@Override
	public boolean isValid(RijksregisternummerForm form, ConstraintValidatorContext context) {
	
		long rijksregisternr = form.getRijksregisternr(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate geboorte=LocalDate.parse(form.getGeboortedatumAlsString(), formatter);
		
		Werknemer werknemer = new Werknemer(); 
		werknemer.setRijksregisternr(rijksregisternr);
		werknemer.setGeboorte(geboorte);
		return werknemer.isGeldigRijksregisternr(); 
		
	}
}