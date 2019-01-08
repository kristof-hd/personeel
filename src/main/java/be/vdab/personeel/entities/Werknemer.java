package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
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

	private Long chefid; 
	
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

	@Column(unique=true)
	private long rijksregisternr; 

	@Version
	private long versie;

	private static final long KLEINSTE_RIJKSREGISTERNR = 10100105L; 
	private static final long GROOTSTE_RIJKSREGISTERNR = 99123199940L; 
	
	public Werknemer(String familienaam, String voornaam, String email, Jobtitel jobtitel, BigDecimal salaris, String paswoord, LocalDate geboorte, long rijksregisternr, long versie) {
		this.familienaam=familienaam;
		this.voornaam=voornaam;
		this.email=email; 
		this.jobtitel=jobtitel; 
		this.salaris=salaris;
		this.paswoord=paswoord;
		this.geboorte=geboorte;
		this.rijksregisternr=rijksregisternr;
		this.versie=versie; 
	}

	public Werknemer(long id, String familienaam, String voornaam, String email, Jobtitel jobtitel, BigDecimal salaris, String paswoord, LocalDate geboorte, long rijksregisternr, long versie) {
		this(familienaam, voornaam, email, jobtitel, salaris, paswoord, geboorte, rijksregisternr, versie);
		this.id=id; 
	}
	
	public Werknemer() {}
	
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

	public Long getChefid() {
		return chefid;
	}

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public LocalDate getGeboorte() {
		return geboorte;
	}

	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public void setGeboorte(LocalDate geboorte) {
		this.geboorte = geboorte;
	}

	public void setRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

	public void opslag(BigDecimal bedrag) {
		if (bedrag.compareTo(BigDecimal.ONE)<0) {
			throw new IllegalArgumentException(); 
		}
		salaris=salaris.add(bedrag); 
	}

	public boolean isGeldigRijksregisternr() {
		return rijksregisternr >= KLEINSTE_RIJKSREGISTERNR && rijksregisternr <= GROOTSTE_RIJKSREGISTERNR && overeenkomstMetGeboortedatum(rijksregisternr, geboorte) && isControlegetalCorrect(rijksregisternr); 
	}

	private boolean overeenkomstMetGeboortedatum(long rijksregisternr, LocalDate geboorte) {
		long eerste6Cijfers = rijksregisternr/100000;
		return (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
				(eerste6Cijfers % 100 == geboorte.getDayOfMonth());   
	}
	
	private boolean isControlegetalCorrect(long rijksregisternr) {

		long eerste9Cijfers=rijksregisternr/100; 
		long controlegetal=rijksregisternr%100;
		
		if (geboorte.getYear() < 2000) {
			return (controlegetal == (97-eerste9Cijfers%97)); 
		}
		else {
			return (controlegetal == (97-(2*Math.pow(10, 9)+eerste9Cijfers)%97));
		}		
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksregisternr ^ (rijksregisternr >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Werknemer other = (Werknemer) object;
		if (rijksregisternr != other.rijksregisternr)
			return false;
		return true;
	}
	
}
