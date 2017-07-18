package ties.hartog.pls_fix.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import ties.hartog.pls_fix.domain.Report;


public class ReportDAO extends BaseDAO{
	
	private List<Report> selectReports(String query) {
		List<Report> results = new ArrayList<Report>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int reportID = dbResultSet.getInt("reportID");
				String title = dbResultSet.getString("title");
				int priority = dbResultSet.getInt("Priority");
				String body = dbResultSet.getString("body");
				int type =  dbResultSet.getInt("type");
				Timestamp datum = dbResultSet.getTimestamp("date");
				int status = dbResultSet.getInt("Status");
				int userID = dbResultSet.getInt("UserID");

				Report newReport = new Report(reportID, title, priority, body, type, datum, status, userID);
				
				results.add(newReport);
				
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
		
	}
	
	
	public boolean create(Report report){
		boolean succes = true;
		String query = "INSERT INTO reports(title, Priority, body, type, Status, UserID) VALUES( ?, ?, ? , ? , ? , ?)" ;		
		
		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, report.getTitle());
	            pstmt.setInt(2, report.getPriority());
	            pstmt.setString(3, report.getBody());
	            pstmt.setInt(4, report.getType());
	            pstmt.setInt(5, report.getStatus());
	            pstmt.setInt(6, report.getUserID());
	            pstmt.executeUpdate();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            succes = false;
	        }
		return succes;
	}
	public Report findById(int reportID) {
		return selectReports("SELECT * FROM reports WHERE reportID = " +reportID).get(0);
	}
	//Read
	public List<Report> getAllBugreports(){
		List<Report> allBugReports = new ArrayList<Report>();
		String query = " SELECT * FROM REPORTS WHERE type = 1";
		allBugReports = selectReports(query);
		return allBugReports;
	}
	
	public List<Report> getAllFeedback(){
		List<Report> allFeedback = new ArrayList<Report>();
		String query = " SELECT * FROM REPORTS WHERE type = 2";
		allFeedback = selectReports(query);
		return allFeedback;
	}

	//UPDATE
	public boolean changeStatus(int reportID, int newStatus){
		boolean succes = true;
		String stmnt = "UPDATE reports SET Status=? WHERE reportid=?" ;
		
		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(stmnt)) {
	            pstmt.setInt(1, newStatus);
	            pstmt.setInt(2, reportID);
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            succes = false;
	        }
		return succes;
	}
	
	public boolean delete(int reportID) {
		boolean result = false;
		boolean reportExists = findById(reportID) != null;
		
		if (reportExists) {
			String stmnt = "DELETE FROM reports WHERE reportid = " +reportID; 
					
			try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(stmnt)){
	            pstmt.executeUpdate();
	            pstmt.close();
				}
				catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return result;
	}
}
	


