package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTest {
	Faker fek;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setup() {
		fek = new Faker();
		userPayload = new User();

		userPayload.setId(fek.idNumber().hashCode());
		userPayload.setEmail(fek.internet().emailAddress());
		userPayload.setFirstName(fek.name().firstName());
		userPayload.setLastName(fek.name().lastName());
		userPayload.setPhone(fek.phoneNumber().cellPhone());
		userPayload.setUsername(fek.name().username());
		userPayload.setPassword(fek.internet().password(5, 10));
		//logs 
		logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority = 0)
	public void testPostUser() {
		
		logger.info("***************Creating User**********************");
		System.out.println("Started the Creation process....");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Creation process is completed");
		logger.info("***************User Created**********************");
		System.out.println("----------------------------------------------------------------");
	}

	@Test(priority = 1)
	public void testGetUser() {
		logger.info("***************Reading User Info**********************");
		System.out.println("Started the fetching process....");
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().body();
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println("Retrieved data process is completed");
		System.out.println("----------------------------------------------------------------");
	}

	@Test(priority = 2)
	public void testUpdateUserByName() {
		logger.info("***************Updating User Info**********************");
		userPayload.setEmail(fek.internet().emailAddress());
		userPayload.setFirstName(fek.name().firstName());
		userPayload.setLastName(fek.name().lastName());
		userPayload.setPhone(fek.phoneNumber().cellPhone());
		System.out.println("Started the update process....");
		Response res = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***************User Info Updated**********************");
		// After Updating details verify it from console
		Response resafterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().body();
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println("Update process is completed");
		System.out.println("----------------------------------------------------------------");
	}

	@Test(priority = 3)
	public void testDeleteByName() {
		logger.info("***************Deleting User**********************");
		System.out.println("Started the delete process....");
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().statusCode(200);
		System.out.println("Delete process is completed");
		logger.info("***************User Deleted**********************");
		System.out.println("----------------------------------------------------------------");
	}
}
