package be.vdab.personeel.web;

import javax.validation.constraints.NotNull;

import be.vdab.personeel.constraints.Rijksregisternummer;

@Rijksregisternummer
public class RijksregisternummerForm {
	
	@NotNull
	private long rijksregisternr;

	private String geboorteAlsString;
	
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

}
