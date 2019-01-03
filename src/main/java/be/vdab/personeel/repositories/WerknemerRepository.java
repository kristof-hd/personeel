package be.vdab.personeel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {

}
