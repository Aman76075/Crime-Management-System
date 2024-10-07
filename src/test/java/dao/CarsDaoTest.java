package dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.*;
import entity.Incidents;
public class CarsDaoTest {
	private CarsDao crime;
	@Before
	public void setup() {
		crime=new CarsDao();
	}
	@After
	public void teardown() {
		crime=null;
	}
	

	@Test
	 public void testCreateIncident() {
        Incidents testIncident = new Incidents(
            10,
            "Robbery",
            java.sql.Date.valueOf("2022-01-15"),
            "banglore",
            "Armed robbery at a convenience store",
            "Open",
            1,
            1
        );
        boolean result = crime.createIncident(testIncident);

        assertTrue("Return true for successful creation of the Incident.", result);
    }
	
	@Test
	public void testUpdateIncidentStatus() {
		int incidentId=1;
		String status="Closed";
		boolean result=crime.updateIncidentStatus(incidentId, status);
		
		assertTrue("Return true for successful updation od Status.",result);
	}

}
