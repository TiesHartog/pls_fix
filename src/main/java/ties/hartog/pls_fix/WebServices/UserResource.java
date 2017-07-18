package ties.hartog.pls_fix.WebServices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ties.hartog.pls_fix.persistence.UserDAO;

@Path("/UserResource")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String getUserID(@FormParam("username") String username, @FormParam("password") String password) {
		
		UserDAO dao = new UserDAO();
		int id = dao.findIDForUsernameAndPassword(username, password);
		System.out.println(id);
		
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", id);
		jab.add(job);
	

	JsonArray array = jab.build();
	return array.toString();
	}
}
