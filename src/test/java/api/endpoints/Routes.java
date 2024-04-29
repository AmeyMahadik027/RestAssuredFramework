package api.endpoints;

//Domain --> //https://petstore.swagger.io/v2 

/*Create User(Post) :- https://petstore.swagger.io/v2/user
Get user (Get) :- https://petstore.swagger.io/v2/user/{username}
Update User (Put):- https://petstore.swagger.io/v2/user/{username}
Delete User (Delete):- https://petstore.swagger.io/v2/user/{username}	
*/

//All urls will be part of Routes class for all modules
public class Routes {
	public static String baseURL = "https://petstore.swagger.io/v2";

	// UserModule
	public static String postURL = baseURL + "/user";
	public static String getURL = baseURL + "/user/{username}";
	public static String putURL = baseURL + "/user/{username}";
	public static String deleteURL = baseURL + "/user/{username}";

	// Store module URL
	// Pet Module URL

}
