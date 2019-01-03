package be.vdab.personeel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
	List<Werknemer> findByJobtitelNaam(@Param("jobtitel") String jobtitel);
	//Werknemer findMetHoogsteHierarchie(); 
}
