package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {
	
	Optional<Werknemer> findById(long id); 
	void update(Werknemer werknemer); 
	List<Werknemer> findByJobtitelNaam(String jobtitel);
	Werknemer findMetHoogsteHierarchie();
	List<Werknemer> findOndergeschikten(long id); 
	Werknemer findChef(long id); 

}
