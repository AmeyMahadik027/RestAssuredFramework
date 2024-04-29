package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=0, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String un, String fname, String lname, String pwd, String emailId, String phone)
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setEmail(emailId);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setPhone(phone);
		userPayload.setUsername(un);
		userPayload.setPassword(pwd);
		
		System.out.println("Started the Creation process....");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Creation process is completed");
		System.out.println("----------------------------------------------------------------");
	
	}
	
	@Test(priority=1, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		
		System.out.println("Started the delete process....");
		Response res = UserEndPoints.deleteUser(userName);
		res.then().statusCode(200);
		System.out.println("Delete process is completed");
		System.out.println("----------------------------------------------------------------");
	
	}

}
