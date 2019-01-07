package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	@Transient
	private List<Werknemer> ondergeschikten;  
	
	@Transient
	private Werknemer chef;
	
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

	public Werknemer(long id, String familienaam, String voornaam, String email, Jobtitel jobtitel, BigDecimal salaris, String paswoord, LocalDate geboorte, long rijksregisternr, long versie) {
		this.id=id; 
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

	public List<Werknemer> getOndergeschikten() {
		return ondergeschikten;
	}

	public Werknemer getChef() {
		return chef;
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

	public long getRijksregisternr() {
		return rijksregisternr;
	}

//	public void setFamilienaam(String familienaam) {
//		this.familienaam = familienaam;
//	}
//
//	public void setVoornaam(String voornaam) {
//		this.voornaam = voornaam;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public void setChefid(Long chefid) {
//		this.chefid = chefid;
//	}

	public void setOndergeschikten(List<Werknemer> ondergeschikten) {
		this.ondergeschikten = ondergeschikten;
	}

	public void setChef(Werknemer chef) {
		this.chef = chef;
	}
	
//	public void setJobtitel(Jobtitel jobtitel) {
//		this.jobtitel = jobtitel;
//	}
//
//	public void setSalaris(BigDecimal salaris) {
//		this.salaris = salaris;
//	}
//
//	public void setPaswoord(String paswoord) {
//		this.paswoord = paswoord;
//	}

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

		long eerste9Cijfers=rijksregisternr/100; 
		long controlegetal=rijksregisternr%100;
		long eerste6Cijfers = rijksregisternr/100000;

		if (geboorte.getYear() < 2000) {
			return ( (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-eerste9Cijfers%97) ); 
		}
		else {
			return ( (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-(2*Math.pow(10, 9)+eerste9Cijfers)%97) );
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Werknemer other = (Werknemer) obj;
		if (rijksregisternr != other.rijksregisternr)
			return false;
		return true;
	}
	
}
