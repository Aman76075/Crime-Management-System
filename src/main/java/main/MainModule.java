package main;

import java.util.Scanner;
import controller.CasesController;
import controller.CasesInterface;
import controller.IncidentsController;
import controller.IncidentsInterface;
import exception.IncidentNumberNotFoundException;
public class MainModule {
	public static void main(String[]args) {
		System.out.println("-----Welcome to Crime Analysis and Reporting System-----");
		IncidentsInterface incidentsInterface = new IncidentsController();
        CasesInterface casesInterface = new CasesController();
        Scanner s = new Scanner(System.in);
        char c;
        try {
        do {
            System.out.println("Enter your choice from the list below: ");
            System.out.println("1. Create an Incident");
            System.out.println("2. Update the status of an incident");
            System.out.println("3. Get a list of incidents within a specified date range");
            System.out.println("4. Search for incidents based on incident type");
            System.out.println("5. Get a list of all the incidents ");
            System.out.println("6. Create a new case");
            System.out.println("8. Update the case details of a particular case");
            System.out.println("9. Get a list of all the cases");

            int choice = s.nextInt();
            switch (choice) {
                case 1: {
                        incidentsInterface.createIncident();
                    
                    break;
                }
                case 2: {
                    try {
                        incidentsInterface.updateIncidentStatus();
                    } catch (IncidentNumberNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    incidentsInterface.getIncidentsInDateRange();
                    break;
                }
                case 4: {
                    incidentsInterface.searchIncidentsByType();
                    break;
                }
                case 5: {
                    incidentsInterface.getAllIncidents();
                    break;
                }
                case 6: {
                    casesInterface.createCase();
                    break;
                }
                case 7: {
                    casesInterface.getCaseDetails();
                    break;
                }
                case 8: {
                    casesInterface.updateCaseDetails();
                    break;
                }
                case 9: {
                    casesInterface.getAllCases();
                    break;
                }
                default: {
                    System.out.println("Choose a proper choice");
                    break;
                }
            }

            System.out.println("Do you want to continue? Y | y");
            c = s.next().charAt(0);
        } while (c=='Y' || c=='y');
        System.out.println("------Thankyou for using the application. Have a nice day.------");
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	s.close();
        }        
	}

}
