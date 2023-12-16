package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserEndpointsTaskFour {


    public static Response getUserDetails(String userId) {
        return RestAssured.given()
                .baseUri(Routes.get_url)
                .basePath("/"+userId)
                .when()
                .get();
    }
    
public static Response deleteUser(int id){
		
		Response res = given()
			.pathParam("id", id)
		.when()
			.delete(Routes.delete_url+"/{id}");
		
		return res;
	}

}