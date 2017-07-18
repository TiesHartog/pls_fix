package ties.hartog.pls_fix.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ties.hartog.pls_fix.domain.User;

public class UserDAO extends BaseDAO {

	private List<User> selectUsers(String query) {
		List<User> results = new ArrayList<User>();

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			;
			while (dbResultSet.next()) {
				int userID = dbResultSet.getInt("userID");
				String name = dbResultSet.getString("name");
				String pass = dbResultSet.getString("password");
				String role = dbResultSet.getString("role");

				User newUser = new User(userID, name, pass, role);

				results.add(newUser);
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("oops");
		}

		return results;

	}

	public String findRoleForUsernameAndPassword(String username, String password) {
		String role = null;
		String query = "SELECT role FROM gebruikers WHERE name = ? AND password = ?";

		try (Connection con = super.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return role;
	}

	public User findById(int userID) {
		return selectUsers("SELECT userID, name, password, role FROM gebruikers WHERE userID = " + userID).get(0);
	}

	public boolean banUser(int userID) {
		boolean succes = false;
		String stmnt = "UPDATE gebruikers SET role=banned WHERE userid=?";
		int bannedOneID = userID;
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(stmnt)) {
			pstmt.setInt(1, bannedOneID);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (findById(bannedOneID).getUrole().equals("banned")) {
			succes = true;
		}
		return succes;
	}

	public void create(User user) {

		String stmnt = "INSERT INTO gebruikers ( name, password, role ) VALUES ( ?, ? , ?)";
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(stmnt)) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUrole());
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public int findIDForUsernameAndPassword(String username, String password) {
		
		String query = "SELECT userID FROM gebruikers WHERE name = ? AND password = ?";

		int id = 0;
		try (Connection con = super.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt("userID");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return id;
	}

}
