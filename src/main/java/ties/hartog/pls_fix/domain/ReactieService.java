package ties.hartog.pls_fix.domain;

import java.util.List;
import ties.hartog.pls_fix.persistence.ReactieDAO;

public class ReactieService {
	ReactieDAO reactieDAO = new ReactieDAO();


	public List<Reactie> getReactiesByReport(int id) {
		return reactieDAO.getReactiesByReport(id);
	}

	public boolean delete(int ID) {
		System.out.println(" Service"+ID);
		return reactieDAO.delete(ID);
	}

	public boolean save(String body, int uID, int repID) {
		Reactie reactie = new Reactie(body, uID, repID);
		return reactieDAO.create(reactie);
	}
	public boolean reactieExists(int ID){
		Reactie reactie = reactieDAO.findById(ID);
		if(reactie == null){
			return false;
		}
		else{
			return true;
		}
	}

}
