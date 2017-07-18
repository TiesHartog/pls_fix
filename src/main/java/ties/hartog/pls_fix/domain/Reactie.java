package ties.hartog.pls_fix.domain;

public class Reactie {
	private int reactieID;
	private String tekst;
	private int userID;
	private int reportID;
	

	public Reactie(int rID, String body, int uID, int repID) {
		reactieID=rID;
		tekst=body;
		userID=uID;
		reportID=repID;
	}
	public Reactie(String body, int uID, int repID) {
		tekst=body;
		userID=uID;
		reportID=repID;
	}

	public int getReactieID() {
		return reactieID;
	}



	public void setReactieID(int reactieID) {
		this.reactieID = reactieID;
	}



	public String getTekst() {
		return tekst;
	}



	public void setTekst(String tekst) {
		this.tekst = tekst;
	}



	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getReportID() {
		return reportID;
	}



	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	
	
	

}
