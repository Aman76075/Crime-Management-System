package controller;
import dao.CarsDao;
import java.util.List;
import java.util.Scanner;
import entity.Cases;
public class CasesController implements CasesInterface {
       public CarsDao crime;
       public Scanner s =new Scanner(System.in);
       
       //NEW CASE CREATION
       public void createCase() {
    	   crime=new CarsDao();
    	   System.out.println("Enter the Case ID:");
    	   int caseId=s.nextInt();
    	   
    	   System.out.println("Enter the Case Description:");
    	   s.nextLine();
    	   String caseDesc=s.nextLine();
    	   
    	   System.out.println("Enter the Incident ID:");
    	   int incidentId=s.nextInt();
    	   
    	   
    	   Cases newC= new Cases();
    	   newC.setCaseId(caseId);
    	   newC.setCaseDescription(caseDesc);
    	   newC.setIncidentsid(incidentId);
    	   
    	   boolean isfiled=false;
    	   try {
    		   isfiled=crime.createCase(newC);
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }
    	   if(isfiled) {
    		   System.out.println("Case Filed Successfully.");
    	   }
    	   else {
    		   System.out.println("Sorry! Case filing is unsuccessfull. Please Try Again.");;
    	   }
       }
       
       //UPDATE CASE DETAILS
       public void updateCaseDetails() {
    	   crime=new CarsDao();
    	   System.out.println("Enter the Case Id to update details:");
    	   int caseId=s.nextInt();
    	   
    	   System.out.println("Enter the updated case description:");
    	   s.nextLine();
    	   String newCaseDesc=s.nextLine();
    	   
    	   System.out.println("Enter the updated Incident Id:");
    	   int newIndidentId=s.nextInt();
    	   
    	   Cases updates=new Cases();
    	   updates.setCaseId(caseId);
    	   updates.setCaseDescription(newCaseDesc);
    	   updates.setIncidentsid(newIndidentId);
    	   
    	   boolean updated=false;
    	   try {
    		   updated=crime.updateCaseDetails(updates);
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }
    	   if(updated) {
    		   System.out.println("Case details has been successfully updated.");
    	   }
    	   else {
    		   System.out.println("Sorry! System failed to update details. Please try again later.");
    	   }
       }
       
       //Get details of a specific case
       
       public void getCaseDetails() {
    	   crime=new CarsDao();
    	   System.out.println("Enter Case Id:");
    	   int caseId=s.nextInt();
    	   s.nextLine();
    	   Cases getdetails=crime.getCaseDetails(caseId);
    	   if(getdetails !=null) {
    		   System.out.println("Case Id: "+getdetails.getCaseId());
    		   System.out.println("Case Description: "+getdetails.getCaseDescription());
    	   }
    	   else {
    		   System.out.println("Case Details NOT FOUND. Check Case Id.");
    	   }
       }
       
       // Get Details of all the cases
       
       public void getAllCases() {
    	   try {
    		   crime=new CarsDao();
    		   List<Cases> allCases=crime.getAllCases();
    		   if(allCases.isEmpty()) {
    			   System.out.println("Cases Not Found.");
    		   }
    		   else {
    			   System.out.println("All Cases Details:");
    			   for(Cases c:allCases) {
    				   System.out.println(c);
    			   }
    		   }
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }
       }   
}
