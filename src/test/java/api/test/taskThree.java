package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;



public class taskThree {
	
	@BeforeTest
	public Response testFetchAllUsers() {
		
		Response res = UserEndPoints.getList();
		res.then().log().all();
		return res;
		
	}
	

	
//	Point-1 - GET
	
	@Test(priority = 1)
	public void testResponseStatusCode() {
		
        Response res = testFetchAllUsers();

//		verify status code is 200
		Assert.assertEquals(res.getStatusCode(), 200, "Status code is not 200");
	}
	

	@Test(priority = 2)
	public void testResponseTotalPages() {
		
        Response res = testFetchAllUsers();

//		Assert 1 - Total number of pages
    	int pageNo = res.path("total_pages");
    	Assert.assertEquals(pageNo, 2, "total number of pages are not equal to 2");
	}
	
	@Test(priority = 3)
	public void testTotalRecords() {
		
        Response res = testFetchAllUsers();

//		Assert 2 - Total records from the response
        
        int totalRecords = res.path("total");
        Assert.assertEquals(totalRecords, 12, "Total records do match the expected count");        
	}
	
	
	@Test(priority = 4)
	public void testTotalRecordsNegative() {
		
        Response res = testFetchAllUsers();

//		Assert 2 - Total records from the response
        
        int totalRecords = res.path("total");
        Assert.assertEquals(totalRecords, 6, "Total records do not match the expected count");  	
	}
	
	
//	point-2 - CREATE
	
	Faker dummyData; //using faker api to generate the random data to insert into POJO class "User"
	User userPayload;
	String id;
	
	
	@BeforeTest
	public void setUpDummyData() {
		dummyData = new Faker();
		userPayload = new User();
		userPayload.setName(dummyData.name().lastName());
		userPayload.setJob(dummyData.job().position());
	}
	
	public Response testCreateUser() {
		
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().body();
		return res;
	    
	}
	
	@Test(priority = 5)
	public void testCreateUserStatusCode() {
        Response res = testCreateUser();

		// Assertion - verify status code
	    Assert.assertEquals(res.getStatusCode(), 201, "Status code is not 201");
		
	}
	
	@Test(priority = 6)
	public void testCreateUserID() {
        Response res = testCreateUser();
        id = res.path("id"); //extracting ID
        
        // Assert the 'id' is not null
        Assert.assertNotNull(id, "ID is null");
        
     // Assert the 'id' is not empty
        Assert.assertTrue(id.trim().length() > 0, "ID is empty");
        
        System.out.println("The ID assigned is: " + id);
     	
	}
	
//	point - 3 - PUT
	@Test(priority = 7)
	public void testPutInfoUpdate() {
		
		userPayload.setName(dummyData.name().name());
		userPayload.setJob(dummyData.job().position());


		Response res = UserEndPoints.updateUser(Integer.parseInt(id), userPayload);
		res.then().log().body();
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 200, "Status code is not equal to 200");
	}
	

//	point - 4 - PATCH
	@Test(priority = 8)
	public void testPatchInfoUpdate() {
		
		userPayload.setName("Umair");

		Response res = UserEndPoints.updatePatchUser(Integer.parseInt(id), userPayload);
		res.then().log().body();
		
		
//		Assertions
		Assert.assertEquals(res.getStatusCode(), 200, "Status code is not equal to 200");
		
	}
	
	
//	point - 5 - DELETE
	
	
	
}
