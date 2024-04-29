package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Class is created for perform Create, Retrieve, delete and update request.
//The given program read data from properties file
public class UserEndPointsThroughPropertiesFile {

	// Method is created for getting urls from properties file
	static ResourceBundle getURL() {
		ResourceBundle route = ResourceBundle.getBundle("routes"); // Load the properties file no need of user directory
																	// path and extension of file.
		return route;
	}

	public static Response createUser(User payload) {
		String postURL = getURL().getString("posturl");
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(postURL);
		return res;
	}

	public static Response readUser(String userName) {
		String getURL = getURL().getString("geturl");
		Response res = given().pathParam("username", userName).when().get(getURL);
		return res;
	}

	public static Response updateUser(String userName, User payload) {
		String putURL = getURL().getString("puturl");
		Response res = given().pathParam("username", userName).contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(payload).when().put(putURL);
		return res;
	}

	public static Response deleteUser(String userName) {
		String deleteURL = getURL().getString("deleteurl");
		Response res = given().pathParam("username", userName).when().delete(deleteURL);
		return res;
	}

}
