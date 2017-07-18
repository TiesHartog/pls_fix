package ties.hartog.pls_fix.WebServices;

import ties.hartog.pls_fix.domain.ReportService;
import ties.hartog.pls_fix.domain.UserService;
import ties.hartog.pls_fix.domain.ReactieService;

public class ServiceProvider {
	private static ReportService reportService = new ReportService();
	private static ReactieService reactieService = new ReactieService();
	private static UserService userService = new UserService();
	
	
	public static ReportService getReportService() {
		return reportService;
	}
	public static ReactieService getReactieService() {
		return reactieService;
	}
	public static UserService getUserService() {
		return userService;
	}
	
}
