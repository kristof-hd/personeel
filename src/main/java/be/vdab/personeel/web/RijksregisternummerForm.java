package be.vdab.personeel.web;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import be.vdab.personeel.constraints.RijksregisternummerFormGeboortedatumEnControlegetal;

@RijksregisternummerFormGeboortedatumEnControlegetal
public class RijksregisternummerForm {
	
	@NotNull
	private long rijksregisternr;

	private String geboorteAlsString;
	//private LocalDate geboorte; 
	

	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public void setRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

	public String getGeboorteAlsString() {
		return geboorteAlsString;
	}

	public void setGeboorteAlsString(String geboorteAlsString) {
		this.geboorteAlsString = geboorteAlsString;
	} 

//	public LocalDate getGeboorte() {
//		return geboorte;
//	}
//
//	public void setGeboorte(LocalDate geboorte) {
//		this.geboorte = geboorte;
//	}
	
	
}
