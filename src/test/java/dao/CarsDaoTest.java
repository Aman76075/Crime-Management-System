package dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import entity.Incidents;
import entity.Cases;
public class CarsDaoTest {
	private CarsDao crime;
	@Before
	public void setup() {
		crime=mock(CarsDao.class);
	}
	@After
	public void teardown() {
		crime=null;
	}
	

	@Test
	 public void testCreateIncident() {
        Incidents testIncident = new Incidents(
            1,
            "Ankit",
            java.sql.Date.valueOf("2022-01-15"),
            "Lucknow",
            "Robbery at a public sector bank.",
            "Open",
            1,
            1
        );
      
        when(crime.createIncident(testIncident)).thenReturn(true);
        
        boolean result = crime.createIncident(testIncident);

        assertTrue(result);
    }
	
	@Test
	public void testUpdateIncidentStatus() {
		int incidentId=10;
		String status="Closed";
		
		when(crime.updateIncidentStatus(incidentId, status)).thenReturn(true);
		
		boolean result=crime.updateIncidentStatus(incidentId,status);
		assertTrue(result);
	}
	
	@Test
	public void testCreateCase() {
		Cases testCase=new Cases(
				1,"Robbery in bank",6
				);
		when(crime.createCase(testCase)).thenReturn(true);
		boolean result=crime.createCase(testCase);
		assertTrue(result);
			
	}

}
