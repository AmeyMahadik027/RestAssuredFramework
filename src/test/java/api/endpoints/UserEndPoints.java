package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Class is created for perform Create, Retrieve, delete and update request.
public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		 Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.postURL);
		return res;
	}

	public static Response readUser(String userName)
	{
		 Response res = given().pathParam("username", userName)
		.when()
		.get(Routes.getURL);
		return res;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response res = given()
				.pathParam("username", userName)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Routes.putURL);
				return res;
	}
	
	
	public static Response deleteUser(String userName)
	{
		Response res = given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.deleteURL);
				return res;
	}


}
