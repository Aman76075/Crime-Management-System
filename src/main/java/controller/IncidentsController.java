package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import entity.Incidents;
import exception.IncidentNumberNotFoundException;
import dao.CarsDao;
public class IncidentsController implements IncidentsInterface {
	public CarsDao crime;
	public Scanner s=new Scanner(System.in);
	
	private Date parseDate(String dateStr)throws ParseException{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate=dateFormat.parse(dateStr);
		return new java.sql.Date(utilDate.getTime());
	}
	
	//Create new incident
	public void createIncident() {
		try {
			crime = new CarsDao();
			Incidents incident=new Incidents();
			System.out.println("Enter Incident Id:");
			int incidentId=s.nextInt();
			s.nextLine();
			incident.setIncidentID(incidentId);
			
			System.out.println("Enter Incident Type:");
			String incidentType=s.nextLine();
			incident.setIncidentType(incidentType);
			
			System.out.println("Enter Incident Date in YYYY-MM-DD format:");
			String incidentDateStr=s.next();
			Date incidentDate=parseDate(incidentDateStr);
			incident.setIncidentDate(incidentDate);
			s.nextLine();
		
			System.out.println("Enter Location: ");
            String location = s.nextLine();
            incident.setLocation(location);

            System.out.println("Enter Description: ");
            String description = s.nextLine();
            incident.setDescription(description);

            System.out.println("Enter Status: ");
            String status = s.nextLine();
            incident.setStatus(status);

            System.out.println("Enter Victim ID: ");
            int victimID = s.nextInt();
            incident.setVictimID(victimID);

            System.out.println("Enter Suspect ID: ");
            int suspectID = s.nextInt();
            incident.setSuspectID(suspectID);
            
            if(crime.createIncident(incident)) {
            	System.out.println("Incident created successfully.");
            }
            else {
            	System.out.println("Sorry! Failed to create incident. Please try again later.");
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//Update Incident with specified Incident ID
	
	public void updateIncidentStatus() throws IncidentNumberNotFoundException{
		try {
			crime=new CarsDao();
			System.out.println("Enter the Incident Id:");
			int incidentId=s.nextInt();
			
			System.out.println("Enter the updated status:");
			String newStatus=s.nextLine();
			
			if(crime.updateIncidentStatus(incidentId,newStatus)) {
				System.out.println("Incident Status updated successfully.");
			}
			else {
				throw new IncidentNumberNotFoundException();
			}
			
		
		}catch(IncidentNumberNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Get Incidents in a specified date range
	
	public void getIncidentsInDateRange() {
        try {
            crime = new CarsDao();
            System.out.println("Enter Starting Date (yyyy-MM-dd): ");
            String startDate = s.next();

            System.out.println("Enter Ending Date (yyyy-MM-dd): ");
            String endDate = s.next();

            List<Incidents> incidentsList = crime.getIncidentsInDateRange(startDate, endDate);

            if (incidentsList.isEmpty()) {
                System.out.println("Sorry! No incidents found in the specified date range.");
            } else {
                System.out.println("Incidents within the specified date range:");
                for (Incidents incident : incidentsList) {
                    System.out.println(incident);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//Search Incidents with a specified Incident type
	
	public void searchIncidentsByType() {
        crime = new CarsDao();
        System.out.println("Enter the Incident Type: ");
        String incidentType = s.next();

        List<Incidents> incidentsList = crime.searchIncidentsByType(incidentType);

        if (incidentsList.isEmpty()) {
            System.out.println("Sorry! No incidents found for the specified type.");
        } else {
            System.out.println("Incidents with the specified type:");
            for (Incidents incident : incidentsList) {
                System.out.println(incident);
            }
        }
    }
	
	//Get details of all the Incidents
	
	public void getAllIncidents() {
        crime = new CarsDao();
        List<Incidents> incidentsList = crime.getAllIncidents();

        if (incidentsList.isEmpty()) {
            System.out.println("Sorry! No incidents were found.");
        } else {
            System.out.println("All Incidents:");
            for (Incidents incident : incidentsList) {
                System.out.println(incident);
            }
        }
    }


}
