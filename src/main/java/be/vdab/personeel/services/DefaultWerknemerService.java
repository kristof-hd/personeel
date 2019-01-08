package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
class DefaultWerknemerService implements WerknemerService {
	private final WerknemerRepository werknemerRepository; 
	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository=werknemerRepository; 
	}

	@Override
	public Optional<Werknemer> findById(long id) {
		return werknemerRepository.findById(id); 
	}
	
	@Override
	public Werknemer findMetHoogsteHierarchie() {
		return werknemerRepository.findMetHoogsteHierarchie(); 
	}

	@Override
	public List<Werknemer> findOndergeschikten(long id) {
		return werknemerRepository.findOndergeschikten(id); 
	} 

	@Override
	public Werknemer findChef(long id) {
		return werknemerRepository.findChef(id);
	} 
	
	@Override
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void update(Werknemer werknemer) {
		werknemerRepository.save(werknemer); 
	}

	@Override
	public List<Werknemer> findByJobtitelNaam(String jobtitel) {
		return werknemerRepository.findByJobtitelNaam(jobtitel); 
	}
}
