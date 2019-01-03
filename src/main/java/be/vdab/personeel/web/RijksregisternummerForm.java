package be.vdab.personeel.web;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

class RijksregisternummerForm {
	
	@NotNull
	@PositiveOrZero //TO DO: eigen annotatie maken
	private BigInteger rijksregisternr;

	public BigInteger getRijksregisternr() {
		return rijksregisternr;
	}

	public void setRijksregisternr(BigInteger rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	} 

}
