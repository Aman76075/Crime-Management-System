package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import entity.Cases;
import entity.Incidents;
import util.DBConnection;
import util.PropertyUtil;

public class CarsDao implements CrimeAnalysisServiceImpl {
	static Connection con;
	Statement statement;
	PreparedStatement preparedstatement;
	ResultSet resultSet;
	private static void getCon()
	{
		if(con==null)
		con=DBConnection.getConnection(PropertyUtil.getPropertyString("db.properties"));
	}
	//New Case Creation by SQL
	public boolean createCase(Cases newCase) {
		getCon();
		try {
			
			
			String sql="Insert into Cases(caseID,caseDescription,incidentID) values (?,?,?)";
			try(PreparedStatement preparedstatement=con.prepareStatement(sql)){
				preparedstatement.setInt(1, newCase.getCaseId());
                preparedstatement.setString(2, newCase.getCaseDescription());
                preparedstatement.setInt(3, newCase.getIncidentsid());

                int affectedRows = preparedstatement.executeUpdate();
                
                return affectedRows>0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(con!=null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Update case details 
	public boolean updateCaseDetails(Cases updatedCase) {
		getCon();
        try {
        	

            String sql = "Update Cases Set caseDescription = ?, incidentID = ? Where caseID = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, updatedCase.getCaseDescription());
                preparedStatement.setInt(2, updatedCase.getIncidentsid());
                preparedStatement.setInt(3, updatedCase.getCaseId());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                
                e.printStackTrace(); 
            }
        }
    }
	
	//Get case details
	public Cases getCaseDetails(int caseId) {
        Cases caseDetails = null;
        getCon();
        try {
        	
            String sql = "Select * From Cases where caseID = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, caseId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        caseDetails = new Cases();
                        caseDetails.setCaseId(resultSet.getInt("caseID"));
                        caseDetails.setCaseDescription(resultSet.getString("caseDescription"));
                        
                    }
                }
            }
        } catch (SQLException e) {            
            e.printStackTrace();
        }
        return caseDetails;
    }
	
	//Get all cases
	public List<Cases> getAllCases() {
        List<Cases> casesList = new ArrayList<>();
        getCon();

        try {
        	

            String sql = "Select * From Cases";
            try (Statement statement = con.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Cases caseObj = new Cases();
                    caseObj.setCaseId(resultSet.getInt("caseID"));
                    caseObj.setCaseDescription(resultSet.getString("caseDescription"));
                    caseObj.setIncidentsid(resultSet.getInt("incidentID"));
                    casesList.add(caseObj);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace(); 
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                
                e.printStackTrace(); 
            }
        }
        return casesList;
    }
	
	//Create incident
	public boolean createIncident(Incidents incident) {
		getCon();
    	try  {
    		

            String sql = "INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Location, Descriptions, Status, VictimID, SuspectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, incident.getIncidentID());
                preparedStatement.setString(2, incident.getIncidentType());
                preparedStatement.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
                preparedStatement.setString(4, incident.getLocation());
                preparedStatement.setString(5, incident.getDescription());
                preparedStatement.setString(6, incident.getStatus());
                preparedStatement.setInt(7, incident.getVictimID());
                preparedStatement.setInt(8, incident.getSuspectID());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
	
	//update incident status
	public boolean updateIncidentStatus(int incidentId,String status) {
		getCon();
    	try {
    		

            String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, incidentId);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
    }
	
	//Get Incidents by specified date range
	public List<Incidents> getIncidentsInDateRange(String startDateStr, String endDateStr) {
        List<Incidents> incidentsList = new ArrayList<>();
        getCon();
        try {
        	
            String sql = "Select * From Incidents Where IncidentDate Between ? And ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = new java.sql.Date(dateFormat.parse(startDateStr).getTime());
                Date endDate = new java.sql.Date(dateFormat.parse(endDateStr).getTime());
                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Incidents incident = new Incidents();
                        incident.setIncidentID(resultSet.getInt("incidentID"));
                        incident.setIncidentType(resultSet.getString("incidentType"));
                        incident.setIncidentDate(resultSet.getDate("incidentDate"));
                        incident.setLocation(resultSet.getString("location"));
                        incident.setDescription(resultSet.getString("descriptions"));
                        incident.setStatus(resultSet.getString("status"));
                        incident.setVictimID(resultSet.getInt("victimID"));
                        incident.setSuspectID(resultSet.getInt("suspectID"));
                        incidentsList.add(incident);
                    }
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return incidentsList;
    }
	
	//Search incident by incident type
	public List<Incidents> searchIncidentsByType(String incidentType) {
        List<Incidents> incidentsList = new ArrayList<>();
    	getCon();
        try {
        

            String sql = "Select * From Incidents Where IncidentType = ?";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, incidentType);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Incidents incident = new Incidents();
                        
               
                        incident.setIncidentID(resultSet.getInt("IncidentID"));
                        incident.setIncidentType(resultSet.getString("IncidentType"));
                        incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                        incident.setLocation(resultSet.getString("Location"));
                        incident.setDescription(resultSet.getString("Descriptions"));
                        incident.setStatus(resultSet.getString("Status"));
                        incident.setVictimID(resultSet.getInt("VictimID"));
                        incident.setSuspectID(resultSet.getInt("SuspectID"));

                        incidentsList.add(incident);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentsList;
    }
	
	//Get list of all the incidents
	public List<Incidents> getAllIncidents() {
        List<Incidents> incidentsList = new ArrayList<>();
        getCon();

        try {
            
            String sql = "SELECT * FROM Incidents";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Incidents incident = new Incidents();
                    
                 
                    incident.setIncidentID(resultSet.getInt("IncidentID"));
                    incident.setIncidentType(resultSet.getString("IncidentType"));
                    incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                    incident.setLocation(resultSet.getString("Location"));
                    incident.setDescription(resultSet.getString("Descriptions"));
                    incident.setStatus(resultSet.getString("Status"));
                    incident.setVictimID(resultSet.getInt("VictimID"));
                    incident.setSuspectID(resultSet.getInt("SuspectID"));

                    incidentsList.add(incident);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return incidentsList;
    }
	

}
