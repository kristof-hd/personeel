package be.vdab.personeel.services;

import java.util.List;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {
	
	Werknemer findById(long id); 
	void update(Werknemer werknemer); 
	List<Werknemer> findByJobtitelNaam(String jobtitel);
	//Werknemer findMetHoogsteHierarchie();
}
