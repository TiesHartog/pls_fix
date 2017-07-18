package ties.hartog.pls_fix.WebServices;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ties.hartog.pls_fix.domain.Reactie;
import ties.hartog.pls_fix.domain.ReactieService;
import ties.hartog.pls_fix.domain.ReportService;
import ties.hartog.pls_fix.domain.UserService;

@Path("/report")
public class ReactieResource {
	ReportService reportService = ServiceProvider.getReportService();
	UserService userService = ServiceProvider.getUserService();
	ReactieService reactieService = ServiceProvider.getReactieService();

	
	@GET	
	@Path("/reactions")
	@Produces("application/json")
	@RolesAllowed({ "user", "admin" })
	public String getReactions(@QueryParam("id") int id)
	{
		List<Reactie> reacties = reactieService.getReactiesByReport(id);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		
		for (Reactie re : reacties)
		{	
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", re.getReactieID());
			job.add("username", userService.findById(re.getUserID()).getUsername());
			job.add("body", re.getTekst());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	@PUT
	@Path("{ID}")
	@RolesAllowed({ "user", "admin" })
	public String createReaction(@PathParam("ID") int ID, @FormParam("react") String body, @FormParam("reportid") int reportID){
		reactieService.save(body, ID, reportID);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("result", "succesfull");
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	

}
