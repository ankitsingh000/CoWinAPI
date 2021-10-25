package methods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class GetMethodParams {
	Response response;
  @Test
  public void execute() {
	  response = given()
			  	
			  	.param("postId","2")
			  	.when()
			  	.get("/comments")
			  	.then()
			  	
			  	.extract().response();

	  System.out.println(response.asString());
	  JsonPath jsonPath = response.jsonPath();
	  String postId = jsonPath.getString("id[0]");
	  System.out.println(postId);
  }
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://jsonplaceholder.typicode.com";
  }

  @AfterTest
  public void afterTest() {
  }

}
