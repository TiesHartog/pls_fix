package ties.hartog.pls_fix.domain;

import java.sql.Timestamp;

public class Report {
	private int reportID;
	private String title;
	private int priority;
	private String body;
	private int type;
	
	private Timestamp datum;
	
	private int status;
	private int userID;
	
	public Report(String titel, int prioriteit, String tekst, int type2, int status2, int uID){
		title= titel;
		priority= prioriteit;
		body= tekst;
		type= type2;
		status= status2;
		userID= uID;	
	}
	
	public Report(int rID, String titel, int prioriteit, String tekst, int type2, Timestamp datum2, int status2, int uID) {
		reportID = rID;
		title= titel;
		priority= prioriteit;
		body= tekst;
		type= type2;
		datum= datum2;
		status= status2;
		userID= uID;
	}
	

		
//	TYPE 1 = BUGREPORTS
//	TYPE 2 = FEEDBACK
	public int getReportID() {
		return reportID;
	}



	public void setReportID(int reportID) {
		this.reportID = reportID;
	}




	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}


	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}


	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}



	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	} 



}
