package be.vdab.personeel.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String naam;
	@Version
	private long versie;

	@OneToMany(mappedBy = "jobtitel")
	private Set<Werknemer> werknemers;

	public Jobtitel(String naam, long versie) {
		this.naam=naam;
		this.versie=versie; 
	}
	
	public Jobtitel() {
	}
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Set<Werknemer> getWerknemers() {
		return Collections.unmodifiableSet(werknemers);
	}

}