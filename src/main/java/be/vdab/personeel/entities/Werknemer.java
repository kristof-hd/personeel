package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="werknemers")
public class Werknemer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String familienaam;

	@NotBlank
	@SafeHtml
	private String voornaam;
	
	@NotNull
	@Email
	private String email; 

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="jobtitelid")
	private Jobtitel jobtitel;

	@NumberFormat(style = Style.NUMBER)
	@NotNull
	@PositiveOrZero
	@Digits(integer = 10, fraction = 2)
	private BigDecimal salaris;
	
	@NotBlank
	@SafeHtml
	private String paswoord; 
	
	@DateTimeFormat(style = "S-")
	@NotNull
	private LocalDate geboorte;

	private BigInteger rijksregisternr; 

	@Version
	private long versie;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getEmail() {
		return email;
	}

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public LocalDate getGeboorte() {
		return geboorte;
	}

	public BigInteger getRijksregisternr() {
		return rijksregisternr;
	}

	public long getVersie() {
		return versie;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setJobtitel(Jobtitel jobtitel) {
		this.jobtitel = jobtitel;
	}

	public void setSalaris(BigDecimal salaris) {
		this.salaris = salaris;
	}

	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}

	public void setGeboorte(LocalDate geboorte) {
		this.geboorte = geboorte;
	}

	public void setRijksregisternr(BigInteger rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

	public void setVersie(long versie) {
		this.versie = versie;
	}

	public void opslag(BigDecimal bedrag) {
		this.salaris.add(bedrag); 
	}
	
}
