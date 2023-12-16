package api.test;

import api.endpoints.UserEndpointsTaskFour;
import api.utilities.DataProviderUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class taskFour {

    public int userIdToDelete;  // Class variable to store the userId to delete

    @Test(dataProvider = "userIds", dataProviderClass = DataProviderUtil.class)
    public void testGetUserDetails(String userId) {
        // Convert userId to integer if needed
        int userIdInt = 0;

        try {
            userIdInt = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            // Log or handle the exception as needed
            System.out.println("Skipping invalid userId: " + userId);
            return; // Skip the test
        }

        // Send a request to get user details using RestAssured
        Response response = UserEndpointsTaskFour.getUserDetails(userId);

        // Add your assertions here
        response.then().log().body();

        // Check the user's attribute, e.g., if id = 3, set userIdToDelete
        if (userIdInt == 3) {
            userIdToDelete = userIdInt;
        }
    }

    
//    point - 3
    
    @Test(priority = 2)
    public void deleteRecord() {
        // Ensure that userIdToDelete is set before attempting to delete
        if (userIdToDelete > 0) {
            Response res = UserEndpointsTaskFour.deleteUser(userIdToDelete);
            Assert.assertEquals(res.getStatusCode(), 204, "Status code is not 204");
        } else {
            // Log or handle the case where userIdToDelete is not set
            System.out.println("No userId to delete");
        }
    }
}
