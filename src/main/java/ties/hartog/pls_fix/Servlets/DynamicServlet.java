package ties.hartog.pls_fix.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ties.hartog.pls_fix.domain.Report;
import ties.hartog.pls_fix.persistence.ReportDAO;
import ties.hartog.pls_fix.persistence.UserDAO;


@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	UserDAO testen = new UserDAO();
	if (testen.logincheck("Ties", "Sfdsa" )){
		System.out.println("yay");
	}
	ReportDAO ff = new ReportDAO();
	List<Report> results  = ff.findByName("o");
	for (Report item : results){
		System.out.println(item.getTitle());
		
	}
	System.out.println(" %");
}
}