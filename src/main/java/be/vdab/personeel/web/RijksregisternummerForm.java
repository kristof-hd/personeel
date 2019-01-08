package be.vdab.personeel.web;

import javax.validation.constraints.NotNull;

import be.vdab.personeel.constraints.Rijksregisternummer;

@Rijksregisternummer
public class RijksregisternummerForm {
	
	@NotNull
	private long rijksregisternr;

	private String geboortedatumAlsString;
	
	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public void setRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

	public String getGeboortedatumAlsString() {
		return geboortedatumAlsString;
	}

	public void setGeboortedatumAlsString(String geboortedatumAlsString) {
		this.geboortedatumAlsString = geboortedatumAlsString;
	} 

}
