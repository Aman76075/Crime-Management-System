package controller;
import exception.IncidentNumberNotFoundException;

public interface IncidentsInterface {
	void createIncident();
	void updateIncidentStatus() throws IncidentNumberNotFoundException;
	void getIncidentsInDateRange();
	void searchIncidentsByType();
	void getAllIncidents();

}
