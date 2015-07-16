package com.pq.ideas.rs.endpoints.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pq.ideas.pojos.DummyPojoForTests;

/**
 * Dummy services, one using GET and other using POST
 * 
 * @author kvillaca
 *
 */

/*
 * We can add /v1 as prefix for dummy (e.g.: /v1/dummy).
 */
@Path("/dummy")
public class DummyService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	

	@POST
	@Path("/serviceOnePost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response serviceOnePost(String value) {
		Response response = null;
		try {
			final DummyPojoForTests dummyTest = parseToPojo(value);
			if (dummyTest != null) {
				LOG.info(dummyTest.toString());
				System.out.println(dummyTest.toString());
				response = Response.ok(dummyTest).build();
			} else {
				response = Response.noContent().build();
			}
		} catch (Exception e) {
			response = Response.status(Response.Status.BAD_REQUEST).build();
		}
		return response;
	}


	@GET
	@Path("/serviceOneGet")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response serviceOneGet(@QueryParam("dummyPojo") String dummyPojo) {
		Response response = null;
		try {
			final DummyPojoForTests dummyTest = parseToPojo(dummyPojo);
			if (dummyTest != null) {
				LOG.info(dummyTest.toString());
				System.out.println(dummyTest.toString());
				response = Response.ok(dummyTest).build();
			} else {
				response = Response.noContent().build();
			}
		} catch (Exception e) {
			response = Response.status(Response.Status.BAD_REQUEST).build();
		}
		return response;
	}
	
	
	/**
	 * Private method to extract the from the JSON payload the desired JSON object
	 * that in this case is the DummyPojoForTests object.
	 * 
	 * @param dummyPojoStr
	 * @return
	 * @throws Exception
	 */
	private DummyPojoForTests parseToPojo(String dummyPojoStr) throws Exception {
		final String POJO_KEY = "DummyPojoForTests";
		final ObjectMapper mapper = new ObjectMapper();
		final JSONObject object = new JSONObject(dummyPojoStr);
		if (object.has(POJO_KEY))
			dummyPojoStr = object.getString(POJO_KEY);
		final DummyPojoForTests dummyTest = mapper.readValue(dummyPojoStr, DummyPojoForTests.class);
		if (dummyTest != null) {
			dummyTest.setMessage("IT'S A RESPONSE NOW");
		}
		dummyPojoStr = null;
		return dummyTest;
	}

}
