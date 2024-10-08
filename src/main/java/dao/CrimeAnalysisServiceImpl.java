package dao;

import java.util.List;
import entity.Incidents;
import entity.Cases;

public interface CrimeAnalysisServiceImpl {
	boolean createCase(Cases newCase);
    Cases getCaseDetails(int caseId);
    boolean updateCaseDetails(Cases updates);
    List<Cases> getAllCases();
    boolean createIncident(Incidents incident);
	boolean updateIncidentStatus(int incidentId, String status);
	List<Incidents> getIncidentsInDateRange(String startDate, String endDate);
    List<Incidents> searchIncidentsByType(String incidentType);
    List<Incidents> getAllIncidents();  


}
