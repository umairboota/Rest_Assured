package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.User;


public class UserEndPoints {
	
	public static int page = 2;
	
	
//	create user endpoint
	public static Response createUser(User payload){
		
		Response res = given()
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	
//	getUser endpoint
	public static Response readUser(int id)
	{
		Response res = given()
			.pathParam("id", id)
		.when()
			.get(Routes.get_url+"/{id}");
		
		return res;
	}
	
	
	
//	update user endpoint
	public static Response updateUser(int id, User payload)
	{
		
		Response res = given()
				.body(payload)
				.pathParam("id", id)
		.when()
			.put(Routes.put_url+"/{id}");
		
		return res;
		
	}
	
//	update user endpoint2
	public static Response updatePatchUser(int id, User payload)
	{
		
		Response res = given()
				.body(payload)
				.pathParam("id", id)
		.when()
			.patch(Routes.put_url+"/{id}");
		
		return res;
		
	}
	
	
//	deleteuser request endpoint
	public static Response deleteUser(int id){
		
		Response res = given()
			.pathParam("id", id)
		.when()
			.delete(Routes.delete_url+"/{id}");
		
		return res;
	}
	
//	getlist
public static Response getList(){
		
		Response res = given()
//			.queryParam("page", page)
		.when()
			.get(Routes.list_users);
		
		return res;
	}


}
