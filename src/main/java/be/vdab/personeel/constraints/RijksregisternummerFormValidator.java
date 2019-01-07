package be.vdab.personeel.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.web.RijksregisternummerForm;

public class RijksregisternummerFormValidator implements ConstraintValidator<RijksregisternummerFormGeboortedatumEnControlegetal, RijksregisternummerForm> {

	@Override
	public void initialize(RijksregisternummerFormGeboortedatumEnControlegetal arg0) {
	}
	
	@Override
	public boolean isValid(RijksregisternummerForm form, ConstraintValidatorContext context) {
	//	if (form.getRijksregisternr() == null) {
	//		return true;
	//	}
		long rijksregisternr = form.getRijksregisternr(); 
		LocalDate geboorte=LocalDate.parse(form.getGeboorteAlsString());
		
		long eerste9Cijfers=rijksregisternr/100; 
		long controlegetal=rijksregisternr%100;
		long eerste6Cijfers = rijksregisternr/100000;
		
		System.out.println(geboorte.getYear());
		System.out.println(geboorte);

		
		if (geboorte.getYear() < 2000) {
			System.out.println(eerste6Cijfers/10000 == geboorte.getYear() % 100); 
			return (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-eerste9Cijfers%97); 
		}
		else {
			return (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-(2*Math.pow(10, 9)+eerste9Cijfers)%97);
		}
	}
}