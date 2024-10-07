package entity;

public class Cases {
	private int caseId;
    private String caseDescription;
    private int Incidentsid;
    
    public Cases(int caseId, String caseDescription, int IncidentsId) {
        this.caseId = caseId;
        this.caseDescription = caseDescription;
        this.Incidentsid = IncidentsId;
    }
    public Cases() {
    	
    }
	public int getCaseId() {
		return caseId;
	}
 
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public int getIncidentsid() {
		return Incidentsid;
	}

	public void setIncidentsid(int incidentsid) {
		Incidentsid = incidentsid;
	}
	@Override
	public String toString() {
        return "Cases{" +
                "caseId=" + caseId +
                ", caseDescription='" + caseDescription + '\'' +
                ", incidentsId=" + Incidentsid +
                '}';
    }

}
