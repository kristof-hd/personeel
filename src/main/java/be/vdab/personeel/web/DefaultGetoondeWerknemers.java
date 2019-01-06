package be.vdab.personeel.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class DefaultGetoondeWerknemers implements Serializable, GetoondeWerknemers {
	private static final long serialVersionUID=1L; 
	private final List<Long> werknemerIds = new LinkedList<>();
	
	@Override
	public void addWerknemerId(long werknemerId) {
		werknemerIds.add(werknemerId);
	}
	
	@Override
	public void removeLastWerknemerId() {
		werknemerIds.remove(werknemerIds.size()-1);
	}
	
	@Override
	public List<Long> getWerknemerIds() {
		return werknemerIds; 
	}
}
