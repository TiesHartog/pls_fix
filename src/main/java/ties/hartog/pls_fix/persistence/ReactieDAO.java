package ties.hartog.pls_fix.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ties.hartog.pls_fix.domain.Reactie;



public class ReactieDAO extends BaseDAO {
	private List<Reactie> selectReacties(String query) {
		List<Reactie> results = new ArrayList<Reactie>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int reactieID = dbResultSet.getInt("reactieID");
				String body = dbResultSet.getString("body");
				int reportID = dbResultSet.getInt("reportID");
				int userID = dbResultSet.getInt("UserID");

				Reactie newReactie = new Reactie(reactieID, body, userID, reportID);
				
				results.add(newReactie);
				
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	public Reactie findById(int reactieID) {
		try{
			 Reactie x = selectReacties("SELECT * FROM reactie WHERE reactieID = " +reactieID).get(0);
			 return x;
			
		}
		catch(IndexOutOfBoundsException e){
			return null;
			
		}
	}
	public boolean create(Reactie reactie){
		
		String query = "INSERT INTO reactie(reportID, body, userID) VALUES( ?, ?, ?)" ;		
		boolean succes = true;
		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setInt(1, reactie.getReportID());	            
	            pstmt.setString(2, reactie.getTekst());
	            pstmt.setInt(3, reactie.getUserID());
	            pstmt.executeUpdate();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("reacDAO" + e.getMessage());
	            succes = false;
	        }
		System.out.println("reaction saved");
		return succes;
	}
	public List<Reactie> getReactiesByReport(int ReportID){
		String query = "SELECT * FROM reactie WHERE reportID =" + ReportID;
		return selectReacties(query);
	}
	
	
	public boolean delete(int reactieID) {
		boolean succes = false;
		boolean reactieExists = findById(reactieID) != null;
		System.out.println(reactieID);
		
		if (reactieExists) {
			String stmnt = "DELETE FROM reactie WHERE reactieID = " +reactieID; 
			System.out.println("ff" +reactieID);
			try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(stmnt)){
	            pstmt.executeUpdate();
	            pstmt.close();
	            succes = true;
				}
				catch (SQLException sqle) {
					System.out.println("ff2" +reactieID);
					sqle.printStackTrace();
		            succes = false;
			}
		}
		return succes;
	}
}
