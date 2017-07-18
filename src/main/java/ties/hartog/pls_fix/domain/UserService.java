package ties.hartog.pls_fix.domain;

import ties.hartog.pls_fix.persistence.UserDAO;
public class UserService {
	UserDAO userDAO = new UserDAO();
	
	public boolean banUser(int UserID){
		 return userDAO.banUser(UserID);
	}
	public User findById(int userID){
		return userDAO.findById(userID);
	}
}
