package be.vdab.personeel.services;

import java.util.Optional;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {
	
	Optional<Werknemer> findById(long id); 
	void update(Werknemer werknemer); 
	Werknemer findMetHoogsteHierarchie();

}
