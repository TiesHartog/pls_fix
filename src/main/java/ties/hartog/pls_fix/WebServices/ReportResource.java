package ties.hartog.pls_fix.WebServices;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;

import ties.hartog.pls_fix.domain.Report;
import ties.hartog.pls_fix.domain.UserService;
import ties.hartog.pls_fix.domain.ReportService;

@Path("/reports")
public class ReportResource {
	ReportService reportService = ServiceProvider.getReportService();
	UserService userService = ServiceProvider.getUserService();

	@GET
	@Path("/bugreports")
	@RolesAllowed({ "user", "admin" })
	@Produces("application/json")
	public String getAllBugReports() {
		List<Report> reports = reportService.getAllBugreports();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Report r : reports) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("title", r.getTitle());
			job.add("body", r.getBody());
			job.add("priority", r.getPriority());
			job.add("date", r.getDatum().toString());
			job.add("id", r.getReportID());
			job.add("username", userService.findById(r.getUserID()).getUsername());
			job.add("status", r.getStatus());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/feedback")
	@GET
	@Produces("application/json")
	@RolesAllowed({ "user", "admin" })
	public String getAllFeedback() {
		List<Report> feedback = reportService.getAllFeedback();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Report f : feedback) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("title", f.getTitle());
			job.add("body", f.getBody());
			job.add("date", f.getDatum().toString());
			job.add("id", f.getReportID());
			job.add("username", userService.findById(f.getUserID()).getUsername());
			job.add("status", f.getStatus());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@POST
	@Path("/newbug{ID}")
	@RolesAllowed({ "user", "admin" })
	public void createBugReport(@PathParam("ID") int uID, @FormParam("title") String titel,
			@FormParam("priority") int prioriteit, @FormParam("body") String tekst) {
		System.out.println("doe iets please");
		reportService.saveBugReport(titel, prioriteit, tekst, uID);

	}

	@POST
	@Path("/newfeedback{ID}")
	@RolesAllowed({ "user", "admin" })
	public void createFeedback(@PathParam("ID") int uID, @FormParam("title") String titel,
			@FormParam("body") String tekst) {
		System.out.println("doe iets please");
		reportService.saveFeedback(titel, tekst, uID);
	}

}
