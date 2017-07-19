package ties.hartog.pls_fix.domain;

import java.util.ArrayList;
import java.util.List;

import ties.hartog.pls_fix.persistence.ReportDAO;

public class ReportService {
	ReportDAO repDAO = new ReportDAO();

	public Report findById(int reportID) {
		return repDAO.findById(reportID);
	}

	public List<Report> getAllBugreports() {
		return repDAO.getAllBugreports();
	}

	public List<Report> getAllFeedback() {
		return repDAO.getAllFeedback();
	}

	public List<Report> findReport(String search) {
		List<Report> all = new ArrayList<Report>();
		List<Report> matches = new ArrayList<Report>();
		for (Report item : all) {
			if (item.getTitle().toLowerCase().contains(search)) {
				matches.add(item);
			}
		}
		return matches;
	}

	public boolean saveFeedback(String titel, String tekst, int uID) {
		Report report = new Report(titel, 0, tekst, 2, 0, uID);
		return repDAO.create(report);
	}

	public boolean saveBugReport(String titel, int prioriteit, String tekst, int uID) {
		Report report = new Report(titel, prioriteit, tekst, 1, 0, uID);
		return repDAO.create(report);
	}

	public boolean changeStatus(int reportID, int newStatus) {
		return repDAO.changeStatus(reportID, newStatus);
	}

}
