package be.vdab.personeel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
	
	List<Werknemer> findByJobtitelNaam(@Param("jobtitel") String jobtitel);
	Werknemer findMetHoogsteHierarchie(); 
	List<Werknemer> findOndergeschikten(long id);
	Werknemer findChef(long id); 

}
