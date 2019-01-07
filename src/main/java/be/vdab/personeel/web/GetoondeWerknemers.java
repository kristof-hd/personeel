package be.vdab.personeel.web;

import java.util.List;

interface GetoondeWerknemers {
	void addWerknemerId(long werknemerId);
	void removeLastWerknemerId();
	public List<Long> getWerknemerIds(); 
	void removeAllWerknemerIds(); 
}
