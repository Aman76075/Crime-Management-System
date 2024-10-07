package exception;

public class IncidentNumberNotFoundException extends Exception {
	public String toString() {
		return "Incident Id not found in the database.";
	}

}
