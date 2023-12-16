package api.test;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class testsCases {
	
static String id;
	
	Faker dummyData;
	User userPayload;
	
	
	@BeforeTest
	public void setUpData() {
		dummyData = new Faker();
		userPayload = new User();
		
		userPayload.setName(dummyData.name().name());
		userPayload.setJob(dummyData.job().position());
	}
	
	@Test(priority=1)
	public void testAllUsersData() {
//		int page = 2;
		Response res = UserEndPoints.getList();
		res.then().log().all();
//		statuscode 
		Assert.assertEquals(res.getStatusCode(), 200);
//		pageNo
		int pageNo = res.jsonPath().getInt("page");
		Assert.assertEquals(pageNo, UserEndPoints.page);
//		perPage users
		int perPage = res.jsonPath().getInt("per_page");
		Assert.assertEquals(perPage, 6);
//		total users
		int total = res.jsonPath().getInt("total");
		Assert.assertEquals(total, 12);
//		headers validate
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
//		get a specific value/firstname in JSONObject -> data:[] -> arry
		String firstName = res.jsonPath().get("data[0].first_name").toString();
		System.out.println(firstName);
//		get a specific value/firstname in JSONObject -> data:[] -> arry
		int id = res.jsonPath().getInt("data[0].id");
		System.out.println(id);
		
//		get all ids
		JSONObject jo = new JSONObject(res.asString());
		
		for(int i=0; i<jo.getJSONArray("data").length(); i++) {
			
			int ids = jo.getJSONArray("data").getJSONObject(i).getInt("id");
			System.out.println(ids);
			
		}
	}
	
	
	@Test(priority=2)
	public void testCreateUser() {
		
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().body();
		id = res.jsonPath().get("id");
		System.out.println(id);
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 201);
	}
	
	@Test(priority = 3)
	public void testgetSingleUser() {
		

		Response res = UserEndPoints.readUser(Integer.parseInt(id));
		res.then().log().body();
		
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 404);
	}
	
	@Test(priority=4)

	public void testPatchInfoUpdate() {
		
		userPayload.setName(dummyData.name().name());

		Response res = UserEndPoints.updatePatchUser(Integer.parseInt(id), userPayload);
		res.then().log().body();
		
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 200);
	}
		
	@Test(priority = 5)
	public void testPutInfoUpdate() {
		
		userPayload.setName(dummyData.name().name());
		userPayload.setJob(dummyData.job().position());


		Response res = UserEndPoints.updateUser(Integer.parseInt(id), userPayload);
		res.then().log().body();
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
}
	
	