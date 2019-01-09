package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.entities.Jobtitel;

public interface JobtitelService {
	List<Jobtitel> findAll();
	Optional<Jobtitel> read(long id);
}
